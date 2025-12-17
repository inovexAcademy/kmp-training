package de.inovex.kmp_training.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.inovex.kmp_training.core.data.repository.CategoryRepository
import de.inovex.kmp_training.core.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CategoriesUiState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val editingCategory: Category? = null,
    val showAddDialog: Boolean = false,
    val newCategoryName: String = "",
    val newCategoryColor: String = Category.DEFAULT_COLORS.first(),
    val error: String? = null
)

class CategoriesViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    
    private val _editingCategory = MutableStateFlow<Category?>(null)
    private val _showAddDialog = MutableStateFlow(false)
    private val _newCategoryName = MutableStateFlow("")
    private val _newCategoryColor = MutableStateFlow(Category.DEFAULT_COLORS.first())
    private val _error = MutableStateFlow<String?>(null)
    
    val uiState: StateFlow<CategoriesUiState> = combine(
        categoryRepository.getAllCategories(),
        _editingCategory,
        _showAddDialog,
        _newCategoryName,
        combine(_newCategoryColor, _error) { color, error -> color to error }
    ) { categories, editingCategory, showAddDialog, newName, colorAndError ->
        val (newColor, error) = colorAndError
        CategoriesUiState(
            categories = categories,
            editingCategory = editingCategory,
            showAddDialog = showAddDialog,
            newCategoryName = newName,
            newCategoryColor = newColor,
            error = error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CategoriesUiState(isLoading = true)
    )
    
    fun showAddDialog() {
        _showAddDialog.value = true
        _newCategoryName.value = ""
        _newCategoryColor.value = Category.DEFAULT_COLORS.random()
    }
    
    fun hideAddDialog() {
        _showAddDialog.value = false
        _editingCategory.value = null
        _newCategoryName.value = ""
        _newCategoryColor.value = Category.DEFAULT_COLORS.first()
    }
    
    fun onNameChange(name: String) {
        _newCategoryName.value = name
    }
    
    fun onColorChange(color: String) {
        _newCategoryColor.value = color
    }
    
    fun startEditing(category: Category) {
        _editingCategory.value = category
        _showAddDialog.value = true
        _newCategoryName.value = category.name
        _newCategoryColor.value = category.colorHex
    }
    
    fun saveCategory() {
        val name = _newCategoryName.value.trim()
        if (name.isBlank()) {
            _error.value = "Category name cannot be empty"
            return
        }
        
        viewModelScope.launch {
            try {
                val editingCategory = _editingCategory.value
                if (editingCategory != null) {
                    categoryRepository.updateCategory(
                        editingCategory.copy(
                            name = name,
                            colorHex = _newCategoryColor.value
                        )
                    )
                } else {
                    categoryRepository.insertCategory(
                        Category(
                            name = name,
                            colorHex = _newCategoryColor.value
                        )
                    )
                }
                hideAddDialog()
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to save category"
            }
        }
    }
    
    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            try {
                categoryRepository.deleteCategory(category)
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to delete category"
            }
        }
    }
    
    fun clearError() {
        _error.value = null
    }
}
