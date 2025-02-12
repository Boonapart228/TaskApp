package com.example.taskapp.presentation.task_editor_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
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
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TaskEditorViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val categoryIdStorage: CategoryIdStorage,
) : ViewModel() {
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

    fun onCreateTaskClick() {
        viewModelScope.launch {
            val currentCategoryId = categoryIdStorage.getId()
            val currentTimeMillis = Calendar.getInstance().timeInMillis
            if (currentCategoryId != null) {
                val newTask = Task(
                    id = 0L,
                    title = _state.value.title,
                    description = _state.value.description,
                    isActive = false,
                    categoryId = currentCategoryId,
                    createdAt = currentTimeMillis
                )
                withContext(Dispatchers.IO) {
                    taskRepository.create(newTask)
                }
            }
            onHomeScreenNavigationClick(Screens.HOME_SCREEN)
        }

    }

}