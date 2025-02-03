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

    private fun getMap(list: MutableMap<String, MutableList<Task>>) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    taskManagerItems = list
                )
            }
        }
    }

    fun onToggleDialogClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(showDialogCreateCategory = !it.showDialogCreateCategory)
            }
        }
    }

    fun createCategory() {
        taskManager.addCategories(_state.value.categoriesTitle)
        viewModelScope.launch {
            _state.update {
                it.copy(
                    categoriesTitle = ""
                )
            }
        }
    }

    fun clearCategoryTitle() {
        viewModelScope.launch {
            _state.update {
                it.copy(categoriesTitle = "")
            }
        }
    }

    fun onCategoryTitleChange(categoriesTitle: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(categoriesTitle = categoriesTitle)
            }
        }
    }

    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.HOME_SCREEN -> viewModelScope.launch { _event.emit(CategoriesNavigationEvent.NavigationToHome) }
            Screens.CATEGORIES_SCREEN -> {}
        }
    }
}