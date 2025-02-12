package com.example.taskapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.DateTimeFormatter
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
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val categoryIdStorage: CategoryIdStorage,
    private val dateTimeFormatter: DateTimeFormatter
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeNavigationEvent>()
    val event = _event.asSharedFlow()

    init {
        val currentCategoryId = categoryIdStorage.getId()
        viewModelScope.launch(Dispatchers.IO) {
            if (currentCategoryId != null) {
                taskRepository.getCurrentTaskById(currentCategoryId).collect { tasks ->
                    _state.update { it.copy(tasks = tasks) }
                }
            }
        }
    }

    fun formatDate(timestamp: Long): String {
        return dateTimeFormatter.formatDate(timestamp)
    }

    fun formatTime(timestamp: Long): String {
        return dateTimeFormatter.formatTime(timestamp)
    }

    fun onNavigationClick(route: Screens) {
        when (route) {
            Screens.HOME_SCREEN -> {}
            Screens.CATEGORIES_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToCategories) }
            Screens.TASK_EDITOR_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToTaskEditor) }
        }
    }
}