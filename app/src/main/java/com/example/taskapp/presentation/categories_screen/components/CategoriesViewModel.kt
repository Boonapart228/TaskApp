package com.example.taskapp.presentation.categories_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.constants.ColorItems
import com.example.taskapp.domain.model.Category
import com.example.taskapp.presentation.categories_screen.model.CategoryOperation
import com.example.taskapp.presentation.navigation.model.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val categoryIdStorage: CategoryIdStorage
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<CategoriesNavigationEvent>()
    val event = _event.asSharedFlow()

    private val _messageEvent = MutableSharedFlow<CategoriesMessageEvent>()
    val messageEvent = _messageEvent.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getAllCategories().collect { categories ->
                _state.update { it.copy(categories = categories) }
            }
        }
    }

    fun onToggleDialogCreateClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    hexColorCode = ColorItems.entries.random().hexColorCode,
                    showDialogCategory = !it.showDialogCategory,
                    categoryOperation = CategoryOperation.CREATE,
                )
            }
        }
    }

    fun onToggleDialogEditClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogCategory = !it.showDialogCategory,
                    categoryOperation = CategoryOperation.EDIT
                )
            }
        }
    }

    fun onConfirmClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogCategory = false,
                    showDialogColorPicker = true
                )
            }
        }
    }

    fun onDismissClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogCategory = false,
                    showDialogColorPicker = false
                )
            }
        }
    }

    fun onBackClick() {
        _state.update {
            it.copy(
                showDialogCategory = true,
                showDialogColorPicker = false
            )
        }
    }

    fun onEditCategoryClick(category: Category) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    categoryTitle = category.title,
                    hexColorCode = category.hexColorCode,
                    currentCategory = category
                )
            }
        }
    }

    fun handleCategory() {
        viewModelScope.launch {
            _state.update {
                it.copy(showDialogColorPicker = false)
            }
            if (_state.value.categoryTitle.isNotBlank()) {
                when (_state.value.categoryOperation) {
                    CategoryOperation.CREATE -> {
                        createCategory()
                    }

                    CategoryOperation.EDIT -> {
                        updateCategory()
                    }
                }
            } else {
                _messageEvent.emit(CategoriesMessageEvent.CategoryTitleCannotBeEmpty)
            }
        }
    }

    private fun updateCategory() {
        viewModelScope.launch {
            val category = _state.value.currentCategory?.copy(
                title = _state.value.categoryTitle,
                hexColorCode = _state.value.hexColorCode
            )
            if (category != null) {
                withContext(Dispatchers.IO) {
                    categoryRepository.updateCategory(category)
                }
                _messageEvent.emit(CategoriesMessageEvent.CategoryUpdateSuccess)
            }
        }
        clearCategoryTitle()
    }

    private fun createCategory() {
        val category = Category(
            title = _state.value.categoryTitle,
            id = 0L,
            hexColorCode = _state.value.hexColorCode
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                categoryRepository.createCategory(category)
            }
            _messageEvent.emit(CategoriesMessageEvent.CategoryCreationSuccess)
        }
        clearCategoryTitle()
    }

    fun clearCategoryTitle() {
        viewModelScope.launch {
            _state.update {
                it.copy(categoryTitle = "")
            }
        }
    }

    fun onCategorySelectClick(id: Long) {
        viewModelScope.launch {
            categoryIdStorage.setId(id)
            onNavigationClick(Screens.HOME_SCREEN)
        }
    }

    fun onCategoryTitleChange(categoriesTitle: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(categoryTitle = categoriesTitle)
            }
        }
    }

    fun onSelectColorClick(hexColorCode: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    hexColorCode = hexColorCode
                )
            }
        }
    }

    fun onNavigationClick(route: Screens) {
        when (route) {
            Screens.HOME_SCREEN -> viewModelScope.launch { _event.emit(CategoriesNavigationEvent.NavigationToHome) }
            Screens.CATEGORIES_SCREEN -> {}
            Screens.TASK_EDITOR_SCREEN -> {}
        }
    }

    fun onDeleteCategoryClick() {
        val currentCategory = _state.value.currentCategory
        currentCategory?.let { category ->
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    categoryRepository.deleteCategory(category)
                }
                resetCurrentCategoryIfDeleted(category.id)
                _state.update { it.copy(currentCategory = null) }
                _messageEvent.emit(CategoriesMessageEvent.CategoryDeletionSuccess)
            }
        }
    }

    private fun resetCurrentCategoryIfDeleted(categoryId: Long) {
        viewModelScope.launch {
            val id = withContext(Dispatchers.IO) { categoryIdStorage.getId() }
            if (categoryId == id) {
                withContext(Dispatchers.IO) { categoryIdStorage.setId(null) }
            }
        }
    }

    fun onToggleDeleteCategoryClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogDeleteCategory = !it.showDialogDeleteCategory
                )
            }
        }
    }

    fun setCurrentCategory(category: Category?) {
        viewModelScope.launch {
            _state.update {
                it.copy(currentCategory = category)
            }
        }
    }

}