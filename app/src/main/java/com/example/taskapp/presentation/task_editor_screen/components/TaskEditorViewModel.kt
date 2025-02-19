package com.example.taskapp.presentation.task_editor_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.TaskIdStorage
import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.constants.ColorItems
import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.navigation.model.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TaskEditorViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val categoryIdStorage: CategoryIdStorage,
    private val taskIdStorage: TaskIdStorage
) : ViewModel() {
    private val _event = MutableSharedFlow<TaskEditorNavigationEvent>()
    val event = _event.asSharedFlow()

    private val _messageEvent = MutableSharedFlow<TaskEditorMessageEvent>()
    val messageEvent = _messageEvent.asSharedFlow()

    private val _state = MutableStateFlow(TaskEditorState())
    val state = _state.asStateFlow()

    init {
        randomHexColorCode()
        taskIdStorage.getId()?.let {
            viewModelScope.launch {
                taskRepository.getCurrentTaskById(taskId = it).collect { task ->
                    _state.update {
                        it.copy(
                            title = task.title,
                            oldTitle = task.title,
                            description = task.description,
                            oldDescription = task.description,
                            pin = task.isActive,
                            oldPin = task.isActive,
                            hexColorCode = task.hexColorCode,
                            oldHexColorCode = task.hexColorCode,
                            previewColorCode = task.hexColorCode
                        )
                    }
                }
            }
        }
    }

    private fun randomHexColorCode() {
        taskIdStorage.getId() ?: run {
            val randomColor = ColorItems.entries.random().hexColorCode
            _state.update { it.copy(hexColorCode = randomColor, previewColorCode = randomColor) }
        }
    }

    private fun detectFieldChanges() {
        _state.update {
            it.copy(
                fieldsChanged = it.title != it.oldTitle || it.description != it.oldDescription || it.pin != it.oldPin || it.hexColorCode != it.oldHexColorCode
            )
        }
    }

    private fun updateFieldChanges() {
        _state.update {
            it.copy(
                oldTitle = it.title,
                oldDescription = it.description,
                oldPin = it.pin,
                oldHexColorCode = it.hexColorCode
            )
        }
    }

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
            detectFieldChanges()
        }
    }

    fun onSetTaskDescription(description: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    description = description
                )
            }
            detectFieldChanges()
        }
    }

    fun onTogglePinTaskClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(pin = !it.pin)
            }
            detectFieldChanges()
        }
    }

    fun onToggleDropDawnMenuClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    expanded = !it.expanded
                )
            }
        }
    }

    fun onEditTaskClick() {
        viewModelScope.launch {
            val currentCategoryId = categoryIdStorage.getId()
            val currentTimeMillis = Calendar.getInstance().timeInMillis
            if (currentCategoryId != null) {
                val newTask = Task(
                    id = 0L,
                    title = _state.value.title,
                    description = _state.value.description,
                    isActive = _state.value.pin,
                    categoryId = currentCategoryId,
                    hexColorCode = _state.value.hexColorCode,
                    createdAt = currentTimeMillis
                )
                handleTask(task = newTask)
            }
        }
    }

    private fun handleTask(task: Task) {
        viewModelScope.launch {
            val taskId = taskIdStorage.getId()
            if (taskId == null) {
                withContext(Dispatchers.IO) {
                    val id = taskRepository.create(task)
                    taskIdStorage.setId(id)
                }
                _messageEvent.emit(TaskEditorMessageEvent.TaskCreationSuccess)
            } else {
                withContext(Dispatchers.IO) {
                    taskRepository.update(task.copy(id = taskId))
                }
                _messageEvent.emit(TaskEditorMessageEvent.TaskUpdateSuccess)
            }
            updateFieldChanges()
            detectFieldChanges()
        }
    }

    fun onToggleColorPickerClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogColorPicker = !it.showDialogColorPicker
                )
            }
        }
    }

    fun onSelectColorCodeClick(previewColorCode: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    previewColorCode = previewColorCode
                )
            }
        }
    }

    fun onSaveColorClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    hexColorCode = it.previewColorCode
                )
            }
        }
        detectFieldChanges()
    }

    fun onToggleDeleteTaskClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showDialogDeleteTask = !it.showDialogDeleteTask
                )
            }
        }
    }

    fun onDeleteTask() {
        viewModelScope.launch {
            val taskId = taskIdStorage.getId()
            if (taskId != null) {
                withContext(Dispatchers.IO) {
                    val id = taskRepository.getCurrentTaskById(taskId).first()
                    taskRepository.delete(task = id.copy(id = taskId))
                }
                _messageEvent.emit(TaskEditorMessageEvent.TaskDeletionSuccess)
                onHomeScreenNavigationClick(Screens.HOME_SCREEN)
            } else {
                _messageEvent.emit(TaskEditorMessageEvent.CannotDeleteNonExistentTask)
            }
        }
    }
}