package de.inovex.kmp_training.ui.screens.taskdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.inovex.kmp_training.core.data.repository.CategoryRepository
import de.inovex.kmp_training.core.data.repository.TaskRepository
import de.inovex.kmp_training.core.model.Category
import de.inovex.kmp_training.core.model.Priority
import de.inovex.kmp_training.core.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class TaskDetailUiState(
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val dueDate: Instant? = null,
    val priority: Priority = Priority.MEDIUM,
    val categoryId: Long? = null,
    val categories: List<Category> = emptyList(),
    val isEditing: Boolean = false,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null,
    val savedSuccessfully: Boolean = false
) {
    val isValid: Boolean
        get() = title.isNotBlank()
}

class TaskDetailViewModel(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository,
    private val taskId: Long?
) : ViewModel() {
    
    private val _title = MutableStateFlow("")
    private val _description = MutableStateFlow("")
    private val _isCompleted = MutableStateFlow(false)
    private val _dueDate = MutableStateFlow<Instant?>(null)
    private val _priority = MutableStateFlow(Priority.MEDIUM)
    private val _categoryId = MutableStateFlow<Long?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _isSaving = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _savedSuccessfully = MutableStateFlow(false)
    
    // Group related flows together
    private data class TaskContent(
        val title: String,
        val description: String,
        val isCompleted: Boolean,
        val dueDate: Instant?,
        val priority: Priority
    )
    
    private data class UiStatus(
        val isLoading: Boolean,
        val isSaving: Boolean,
        val error: String?,
        val savedSuccessfully: Boolean
    )
    
    private val taskContentFlow = combine(
        _title,
        _description,
        _isCompleted,
        _dueDate,
        _priority
    ) { title, description, isCompleted, dueDate, priority ->
        TaskContent(title, description, isCompleted, dueDate, priority)
    }
    
    private val uiStatusFlow = combine(
        _isLoading,
        _isSaving,
        _error,
        _savedSuccessfully
    ) { isLoading, isSaving, error, savedSuccessfully ->
        UiStatus(isLoading, isSaving, error, savedSuccessfully)
    }
    
    val uiState: StateFlow<TaskDetailUiState> = combine(
        taskContentFlow,
        _categoryId,
        categoryRepository.getAllCategories(),
        uiStatusFlow
    ) { content, categoryId, categories, status ->
        TaskDetailUiState(
            title = content.title,
            description = content.description,
            isCompleted = content.isCompleted,
            dueDate = content.dueDate,
            priority = content.priority,
            categoryId = categoryId,
            categories = categories,
            isEditing = taskId != null,
            isLoading = status.isLoading,
            isSaving = status.isSaving,
            error = status.error,
            savedSuccessfully = status.savedSuccessfully
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskDetailUiState(isLoading = taskId != null)
    )
    
    init {
        if (taskId != null) {
            loadTask(taskId)
        }
    }
    
    private fun loadTask(id: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            val task = taskRepository.getTaskById(id)
            if (task != null) {
                _title.value = task.title
                _description.value = task.description
                _isCompleted.value = task.isCompleted
                _dueDate.value = task.dueDate
                _priority.value = task.priority
                _categoryId.value = task.categoryId
            }
            _isLoading.value = false
        }
    }
    
    fun onTitleChange(title: String) {
        _title.value = title
    }
    
    fun onDescriptionChange(description: String) {
        _description.value = description
    }
    
    fun onCompletedChange(isCompleted: Boolean) {
        _isCompleted.value = isCompleted
    }
    
    fun onDueDateChange(dueDate: Instant?) {
        _dueDate.value = dueDate
    }
    
    fun onPriorityChange(priority: Priority) {
        _priority.value = priority
    }
    
    fun onCategoryChange(categoryId: Long?) {
        _categoryId.value = categoryId
    }
    
    fun saveTask() {
        if (_title.value.isBlank()) {
            _error.value = "Title cannot be empty"
            return
        }
        
        viewModelScope.launch {
            _isSaving.value = true
            _error.value = null
            
            try {
                val task = Task(
                    id = taskId ?: 0,
                    title = _title.value,
                    description = _description.value,
                    isCompleted = _isCompleted.value,
                    dueDate = _dueDate.value,
                    priority = _priority.value,
                    categoryId = _categoryId.value,
                    createdAt = Clock.System.now()
                )
                
                if (taskId != null) {
                    taskRepository.updateTask(task)
                } else {
                    taskRepository.insertTask(task)
                }
                
                _savedSuccessfully.value = true
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to save task"
            } finally {
                _isSaving.value = false
            }
        }
    }
    
    fun clearError() {
        _error.value = null
    }
}
