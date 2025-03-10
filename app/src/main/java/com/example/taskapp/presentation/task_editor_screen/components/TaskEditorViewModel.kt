package com.example.taskapp.presentation.task_editor_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.constants.ColorItems
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.usecase.category_storage.GetCategoryIdUseCase
import com.example.taskapp.domain.usecase.task.GetCurrentTaskByIdUseCase
import com.example.taskapp.domain.usecase.task_actions.CreateTaskUseCase
import com.example.taskapp.domain.usecase.task_actions.DeleteTaskUseCase
import com.example.taskapp.domain.usecase.task_actions.UpdateTaskUseCase
import com.example.taskapp.domain.usecase.task_storage.GetTaskIdUseCase
import com.example.taskapp.domain.usecase.task_storage.SetTaskIdUseCase
import com.example.taskapp.domain.usecase.title_formatter.GetCorrectTitleUseCase
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
import javax.inject.Provider

@HiltViewModel
class TaskEditorViewModel @Inject constructor(
    private val getCategoryIdStorage: Provider<GetCategoryIdUseCase>,
    private val getCorrectTitleUseCase: Provider<GetCorrectTitleUseCase>,
    private val setTaskIdUseCase: Provider<SetTaskIdUseCase>,
    private val getTaskIdUseCase: Provider<GetTaskIdUseCase>,
    private val getCurrentTaskByIdUseCase: Provider<GetCurrentTaskByIdUseCase>,
    private val createTaskUseCase: Provider<CreateTaskUseCase>,
    private val updateTaskUseCase: Provider<UpdateTaskUseCase>,
    private val deleteTaskUseCase: Provider<DeleteTaskUseCase>
) : ViewModel() {
    private val _event = MutableSharedFlow<TaskEditorNavigationEvent>()
    val event = _event.asSharedFlow()

    private val _messageEvent = MutableSharedFlow<TaskEditorMessageEvent>()
    val messageEvent = _messageEvent.asSharedFlow()

    private val _state = MutableStateFlow(TaskEditorState())
    val state = _state.asStateFlow()

    init {
        randomHexColorCode()
        getCurrentTask()
        updateLastOpenAt()
    }

    private fun getCurrentTask() {
        getTaskIdUseCase.get().execute()?.let {
            viewModelScope.launch {
                getCurrentTaskByIdUseCase.get().execute(taskId = it).collect { task ->
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
                            previewColorCode = task.hexColorCode,
                            categoryId = task.categoryId
                        )
                    }
                }
            }

        }
    }

    private fun updateLastOpenAt() {
        viewModelScope.launch {
            val taskId = getTaskIdUseCase.get().execute()
            if (taskId != null) {
                val currentTimeMillis = Calendar.getInstance().timeInMillis
                withContext(Dispatchers.IO) {
                    val task = getCurrentTaskByIdUseCase.get().execute(taskId = taskId).first()
                    updateTaskUseCase.get().execute(task.copy(lastActiveAt = currentTimeMillis))
                }
            }
        }
    }

    private fun randomHexColorCode() {
        getTaskIdUseCase.get().execute() ?: run {
            val randomColor = ColorItems.entries.random().hexColorCode
            _state.update { it.copy(hexColorCode = randomColor, previewColorCode = randomColor) }
        }
    }

    private fun detectFieldChanges() {
        _state.update {
            it.copy(
                fieldsChanged = it.title != it.oldTitle ||
                        it.description != it.oldDescription
                        || it.pin != it.oldPin
                        || it.hexColorCode
                        != it.oldHexColorCode
            )
        }
    }

    private fun updateFieldChanges() {
        _state.update {
            it.copy(
                title = getCorrectTitleUseCase.get().execute(_state.value.title),
                oldTitle = getCorrectTitleUseCase.get().execute(_state.value.title),
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
            Screens.SETTING_SCREEN -> {}
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
            val currentCategoryId = getCategoryIdStorage.get().execute()
            val currentTimeMillis = Calendar.getInstance().timeInMillis
            if (currentCategoryId != null) {
                val newTask = Task(
                    id = 0L,
                    title = getCorrectTitleUseCase.get().execute(_state.value.title),
                    description = _state.value.description,
                    isActive = _state.value.pin,
                    categoryId = _state.value.categoryId ?: currentCategoryId,
                    hexColorCode = _state.value.hexColorCode,
                    createdAt = currentTimeMillis,
                    lastActiveAt = currentTimeMillis
                )
                handleTask(task = newTask)
            }
        }
    }

    private fun handleTask(task: Task) {
        viewModelScope.launch {
            if (_state.value.title.isBlank()) {
                _messageEvent.emit(TaskEditorMessageEvent.TaskTitleCannotBeEmpty)
                return@launch
            }
            val taskId = getTaskIdUseCase.get().execute()
            if (taskId == null) {
                withContext(Dispatchers.IO) {
                    val id = createTaskUseCase.get().execute(task)
                    setTaskIdUseCase.get().execute(id)
                }
                _messageEvent.emit(TaskEditorMessageEvent.TaskCreationSuccess)
            } else {
                withContext(Dispatchers.IO) {
                    updateTaskUseCase.get().execute(task.copy(id = taskId))
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
            val taskId = getTaskIdUseCase.get().execute()
            if (taskId != null) {
                withContext(Dispatchers.IO) {
                    val id = getCurrentTaskByIdUseCase.get().execute(taskId).first()
                    deleteTaskUseCase.get().execute(task = id.copy(id = taskId))
                }
                _messageEvent.emit(TaskEditorMessageEvent.TaskDeletionSuccess)
                onHomeScreenNavigationClick(Screens.HOME_SCREEN)
            } else {
                _messageEvent.emit(TaskEditorMessageEvent.CannotDeleteNonExistentTask)
            }
        }
    }
}