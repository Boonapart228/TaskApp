package com.example.taskapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.AppSettings
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.DateTimeFormatter
import com.example.taskapp.domain.TaskIdStorage
import com.example.taskapp.domain.TaskRepository
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
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val categoryIdStorage: CategoryIdStorage,
    private val dateTimeFormatter: DateTimeFormatter,
    private val appSettings: AppSettings,
    private val taskIdStorage: TaskIdStorage
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeNavigationEvent>()
    val event = _event.asSharedFlow()

    private val _messageEvent = MutableSharedFlow<HomeMessageEvent>()
    val messageEvent = _messageEvent.asSharedFlow()

    init {
        val currentCategoryId = categoryIdStorage.getId()
        viewModelScope.launch(Dispatchers.IO) {
            if (currentCategoryId != null) {
                taskRepository.getCurrentTasksById(currentCategoryId).collect { tasks ->
                    _state.update { it.copy(tasks = tasks) }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    gridColumns = appSettings.getGridColumns()
                )
            }
        }
    }

    fun onChangeGridColumnsClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(gridColumns = if (it.gridColumns == 2) 1 else 2)
            }
            withContext(Dispatchers.IO) {
                appSettings.setGridColumns(_state.value.gridColumns)
            }
        }
    }

    fun formatDate(timestamp: Long): String {
        return dateTimeFormatter.formatDate(timestamp)
    }

    fun formatTime(timestamp: Long): String {
        return dateTimeFormatter.formatTime(timestamp)
    }

    fun onTaskSelectClick(id: Long) {
        viewModelScope.launch {
            taskIdStorage.setId(id)
            onNavigationClick(Screens.TASK_EDITOR_SCREEN)
        }
    }

    private fun clearTaskId() {
        taskIdStorage.setId(null)
    }

    fun onNavigateToTaskEditorClick() {
        viewModelScope.launch {
            if (categoryIdStorage.getId() != null) {
                clearTaskId()
                onNavigationClick(Screens.TASK_EDITOR_SCREEN)
            } else {
                _messageEvent.emit(HomeMessageEvent.ChoseCategory)
            }
        }
    }

    fun onNavigationClick(route: Screens) {
        when (route) {
            Screens.HOME_SCREEN -> {}
            Screens.CATEGORIES_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToCategories) }
            Screens.TASK_EDITOR_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToTaskEditor) }
        }
    }
}