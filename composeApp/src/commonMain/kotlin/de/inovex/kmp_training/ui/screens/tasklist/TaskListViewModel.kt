package de.inovex.kmp_training.ui.screens.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.inovex.kmp_training.core.data.repository.CategoryRepository
import de.inovex.kmp_training.core.data.repository.TaskRepository
import de.inovex.kmp_training.core.model.Category
import de.inovex.kmp_training.core.model.Priority
import de.inovex.kmp_training.core.model.Task
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class SortOption(val displayName: String) {
    CREATED_DATE("Date Created"),
    DUE_DATE("Due Date"),
    PRIORITY("Priority"),
    ALPHABETICAL("Name")
}

data class FilterState(
    val showCompleted: Boolean = true,
    val selectedCategoryId: Long? = null,
    val selectedPriority: Priority? = null
)

data class TaskListUiState(
    val tasks: List<Task> = emptyList(),
    val categories: List<Category> = emptyList(),
    val searchQuery: String = "",
    val filterState: FilterState = FilterState(),
    val sortOption: SortOption = SortOption.CREATED_DATE,
    val isLoading: Boolean = false,
    val isSyncing: Boolean = false,
    val error: String? = null
)

@OptIn(FlowPreview::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class TaskListViewModel(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    
    private val _searchQuery = MutableStateFlow("")
    private val _filterState = MutableStateFlow(FilterState())
    private val _sortOption = MutableStateFlow(SortOption.CREATED_DATE)
    private val _isSyncing = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    
    private val debouncedSearchQuery = _searchQuery.debounce(300)
    
    private val tasksFlow = combine(
        debouncedSearchQuery,
        _filterState,
        _sortOption
    ) { query, filter, sort ->
        Triple(query, filter, sort)
    }.flatMapLatest { (query, filter, _) ->
        when {
            query.isNotBlank() -> taskRepository.searchTasks(query)
            filter.selectedCategoryId != null -> taskRepository.getTasksByCategory(filter.selectedCategoryId)
            filter.selectedPriority != null -> taskRepository.getTasksByPriority(filter.selectedPriority)
            else -> taskRepository.getAllTasks()
        }
    }
    
    val uiState: StateFlow<TaskListUiState> = combine(
        tasksFlow,
        categoryRepository.getAllCategories(),
        _searchQuery,
        _filterState,
        combine(_sortOption, _isSyncing, _error) { sort, syncing, error -> Triple(sort, syncing, error) }
    ) { tasks, categories, searchQuery, filterState, extras ->
        val (sortOption, isSyncing, error) = extras
        
        val filteredTasks = tasks
            .filter { task ->
                (filterState.showCompleted || !task.isCompleted) &&
                (filterState.selectedPriority == null || task.priority == filterState.selectedPriority)
            }
            .let { list ->
                when (sortOption) {
                    SortOption.CREATED_DATE -> list.sortedByDescending { it.createdAt }
                    SortOption.DUE_DATE -> list.sortedBy { it.dueDate ?: kotlinx.datetime.Instant.DISTANT_FUTURE }
                    SortOption.PRIORITY -> list.sortedByDescending { it.priority.level }
                    SortOption.ALPHABETICAL -> list.sortedBy { it.title.lowercase() }
                }
            }
        
        TaskListUiState(
            tasks = filteredTasks,
            categories = categories,
            searchQuery = searchQuery,
            filterState = filterState,
            sortOption = sortOption,
            isSyncing = isSyncing,
            error = error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskListUiState(isLoading = true)
    )
    
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
    
    fun onFilterChange(filterState: FilterState) {
        _filterState.value = filterState
    }
    
    fun onSortOptionChange(sortOption: SortOption) {
        _sortOption.value = sortOption
    }
    
    fun toggleShowCompleted() {
        _filterState.value = _filterState.value.copy(
            showCompleted = !_filterState.value.showCompleted
        )
    }
    
    fun selectCategory(categoryId: Long?) {
        _filterState.value = _filterState.value.copy(selectedCategoryId = categoryId)
    }
    
    fun selectPriority(priority: Priority?) {
        _filterState.value = _filterState.value.copy(selectedPriority = priority)
    }
    
    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            taskRepository.toggleTaskCompletion(task.id, !task.isCompleted)
        }
    }
    
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
    
    fun syncWithRemote() {
        viewModelScope.launch {
            _isSyncing.value = true
            _error.value = null
            
            taskRepository.fetchRemoteTasks()
                .onSuccess {
                    _isSyncing.value = false
                }
                .onFailure { e ->
                    _error.value = e.message ?: "Failed to sync"
                    _isSyncing.value = false
                }
        }
    }
    
    fun clearError() {
        _error.value = null
    }
}
