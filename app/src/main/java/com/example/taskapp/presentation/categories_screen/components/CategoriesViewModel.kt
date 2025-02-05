package com.example.taskapp.presentation.categories_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.TaskManager
import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.navigation.model.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val taskManager: TaskManager
) : ViewModel() {
    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<CategoriesNavigationEvent>()
    val event = _event.asSharedFlow()

    init {
        getMap(taskManager.getAllTaskManager())
    }

    private fun getMap(map: MutableMap<String, MutableList<Task>>) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    taskManagerItems = map
                )
            }
        }
    }

    fun onToggleDialogCreateClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogCreateCategory = !it.showDialogCreateCategory,
                )
            }
        }
    }

    fun onToggleDialogUpdateClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogUpdateCategory = !it.showDialogUpdateCategory,
                )
            }
        }
    }

    fun onEditCategoryClick(category: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(categoryTitle = category, oldCategoryTitle = category)
            }
        }
    }

    fun updateCategory() {
        viewModelScope.launch {
            taskManager.updateCategory(
                oldCategoryTitle = _state.value.oldCategoryTitle,
                newCategoryTitle = _state.value.categoryTitle
            )
            _state.update {
                it.copy(
                    categoryTitle = "",
                    oldCategoryTitle = ""
                )
            }
        }
    }

    fun onDeleteCategoryClick(category: String) {
        viewModelScope.launch {
            taskManager.deleteCategory(category)
        }
    }

    fun createCategory() {
        taskManager.addCategory(_state.value.categoryTitle)
        viewModelScope.launch {
            _state.update {
                it.copy(
                    categoryTitle = ""
                )
            }
        }
    }

    fun clearCategoryTitle() {
        viewModelScope.launch {
            _state.update {
                it.copy(categoryTitle = "")
            }
        }
    }

    fun onCategoryTitleChange(categoriesTitle: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(categoryTitle = categoriesTitle)
            }
        }
    }

    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.HOME_SCREEN -> viewModelScope.launch { _event.emit(CategoriesNavigationEvent.NavigationToHome) }
            Screens.CATEGORIES_SCREEN -> {}
            Screens.TASK_EDITOR_SCREEN -> {}
        }
    }
}