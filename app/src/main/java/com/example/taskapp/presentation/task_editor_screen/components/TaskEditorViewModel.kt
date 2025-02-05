package com.example.taskapp.presentation.task_editor_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class TaskEditorViewModel @Inject constructor() : ViewModel() {
    private val _event = MutableSharedFlow<TaskEditorNavigationEvent>()
    val event = _event.asSharedFlow()

    private val _state = MutableStateFlow(TaskEditorState())
    val state = _state.asStateFlow()

    fun onHomeScreenNavigationClick(route: Screens) {
        when (route) {
            Screens.HOME_SCREEN -> {
                viewModelScope.launch { _event.emit(TaskEditorNavigationEvent.NavigationToHome) }
            }

            Screens.CATEGORIES_SCREEN -> {}
            Screens.TASK_EDITOR_SCREEN -> {}
        }
    }

    fun onSetTaskTitle(title: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    title = title
                )
            }
        }
    }

    fun onSetTaskDescription(description: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    description = description
                )
            }
        }
    }

}