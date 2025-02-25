package com.example.taskapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.AppSettings
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.DateTimeFormatter
import com.example.taskapp.domain.TaskIdStorage
import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.constants.SortDirection
import com.example.taskapp.presentation.home_screen.model.HomeSortParameter
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
        getActiveTasks()

        getInActiveTasks()

        getGridColumns()
    }

    private fun getGridColumns() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    gridColumns = appSettings.getGridColumns()
                )
            }
        }
    }


    private fun getInActiveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentCategoryId = categoryIdStorage.getId()
            if (currentCategoryId != null) {
                taskRepository.getInActiveTasks(
                    categoryId = currentCategoryId,
                    sortBy = _state.value.unpinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.unpinnedSortDirection.direction
                ).collect { inActiveTasks ->
                    _state.update { it.copy(inActiveTasks = inActiveTasks) }
                }
            }
        }
    }

    private fun getActiveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentCategoryId = categoryIdStorage.getId()
            if (currentCategoryId != null) {
                taskRepository.getActiveTasks(
                    categoryId = currentCategoryId,
                    sortBy = _state.value.pinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.pinnedSortDirection.direction
                )
                    .collect { activeTasks ->
                        _state.update { it.copy(activeTasks = activeTasks) }
                    }
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

    fun onPinnedDirectionChange(sortDirection: SortDirection) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    pinnedSortDirection = sortDirection
                )
            }
        }
        getActiveTasks()
    }

    fun onPinnedSortParameterChange(homeSortParameter: HomeSortParameter) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    pinnedHomeSortParameter = homeSortParameter
                )
            }
        }
        getActiveTasks()
    }

    fun onTogglePinnedMenuClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showPinnedSortDialog = !it.showPinnedSortDialog
                )
            }
        }
    }

    fun onUnPinnedDirectionChange(sortDirection: SortDirection) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    unpinnedSortDirection = sortDirection
                )
            }
        }
        getInActiveTasks()
    }

    fun onUnPinnedSortParameterChange(homeSortParameter: HomeSortParameter) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    unpinnedHomeSortParameter = homeSortParameter
                )
            }
        }
        getInActiveTasks()
    }

    fun onToggleUnPinnedMenuClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showUnpinnedSortDialog = !it.showUnpinnedSortDialog
                )
            }
        }
    }
}