package com.example.taskapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.constants.SortDirection
import com.example.taskapp.domain.usecase.category_storage.GetCategoryIdUseCase
import com.example.taskapp.domain.usecase.date_formatter.FormatDateUseCase
import com.example.taskapp.domain.usecase.date_formatter.FormatTimeUseCase
import com.example.taskapp.domain.usecase.settings.GetGridColumnsUseCase
import com.example.taskapp.domain.usecase.settings.GetRecentNoteFilterUseCase
import com.example.taskapp.domain.usecase.settings.SetGridColumnsUseCase
import com.example.taskapp.domain.usecase.task.GetActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetActiveTasksUseCase
import com.example.taskapp.domain.usecase.task.GetAllActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetAllInActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetInActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetInActiveTasksUseCase
import com.example.taskapp.domain.usecase.task_storage.SetTaskIdUseCase
import com.example.taskapp.presentation.home_screen.model.HomeSortParameter
import com.example.taskapp.presentation.home_screen.model.NotesFilterType
import com.example.taskapp.presentation.home_screen.model.TaskType
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
import javax.inject.Provider

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryIdUseCase: Provider<GetCategoryIdUseCase>,
    private val formatDateUseCase: Provider<FormatDateUseCase>,
    private val formatTimeUseCase: Provider<FormatTimeUseCase>,
    private val setTaskIdUseCase: Provider<SetTaskIdUseCase>,
    private val setGridColumnsUseCase: Provider<SetGridColumnsUseCase>,
    private val getGridColumnsUseCase: Provider<GetGridColumnsUseCase>,
    private val getRecentNoteFilterUseCase: Provider<GetRecentNoteFilterUseCase>,
    private val getActiveTasksUseCase: Provider<GetActiveTasksUseCase>,
    private val getInActiveTasksUseCase: Provider<GetInActiveTasksUseCase>,
    private val getActiveRecentTasksUseCase: Provider<GetActiveRecentTasksUseCase>,
    private val getInActiveRecentTasksUseCase: Provider<GetInActiveRecentTasksUseCase>,
    private val getAllActiveRecentTasksUseCase: Provider<GetAllActiveRecentTasksUseCase>,
    private val getAllInActiveRecentTasksUseCase: Provider<GetAllInActiveRecentTasksUseCase>
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeNavigationEvent>()
    val event = _event.asSharedFlow()

    private val _messageEvent = MutableSharedFlow<HomeMessageEvent>()
    val messageEvent = _messageEvent.asSharedFlow()

    init {
        handleActiveTasks()

        handleInActiveTasks()

        getGridColumns()
    }


    private fun handleActiveTasks() {
        when (_state.value.notesFilterType) {
            NotesFilterType.ALL -> {
                getActiveTasks()
            }

            NotesFilterType.RECENT -> {
                getActiveRecentTasks()
            }
        }
    }

    private fun handleInActiveTasks() {
        when (_state.value.notesFilterType) {
            NotesFilterType.ALL -> {
                getInActiveTasks()
            }

            NotesFilterType.RECENT -> {
                getInActiveRecentTasks()
            }
        }
    }

    private fun getActiveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentCategoryId = getCategoryIdUseCase.get().execute()
            if (currentCategoryId != null) {
                getActiveTasksUseCase.get().execute(
                    categoryId = currentCategoryId,
                    sortBy = _state.value.pinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.pinnedSortDirection.direction
                ).collect { activeTasks ->
                    _state.update { it.copy(activeTasks = activeTasks) }
                }
            } else {
                _state.update { it.copy(activeTasks = listOf()) }
            }
        }
    }

    private fun getActiveRecentTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentCategoryId = getCategoryIdUseCase.get().execute()
            val noteFilter = getRecentNoteFilterUseCase.get().execute().type
            if (currentCategoryId != null) {
                getActiveRecentTasksUseCase.get().execute(
                    noteFilter = noteFilter,
                    currentCategoryId = currentCategoryId,
                    sortBy = _state.value.pinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.pinnedSortDirection.direction
                ).collect { activeTasks ->
                    _state.update { it.copy(activeTasks = activeTasks) }
                }
            } else {
                getAllActiveRecentTasksUseCase.get().execute(
                    sortBy = _state.value.pinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.pinnedSortDirection.direction
                ).collect { activeTasks ->
                    _state.update { it.copy(activeTasks = activeTasks) }
                }
            }
        }
    }


    private fun getGridColumns() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    gridColumns = getGridColumnsUseCase.get().execute()
                )
            }
        }
    }

    fun onChangeNoteFilterType(notesFilterType: NotesFilterType) {
        viewModelScope.launch {
            _state.update {
                it.copy(notesFilterType = notesFilterType)
            }
        }
        handleActiveTasks()
        handleInActiveTasks()
    }


    private fun getInActiveRecentTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentCategoryId = getCategoryIdUseCase.get().execute()
            val noteFilter = getRecentNoteFilterUseCase.get().execute().type

            if (currentCategoryId != null) {
                getInActiveRecentTasksUseCase.get().execute(
                    noteFilter = noteFilter,
                    currentCategoryId = currentCategoryId,
                    sortBy = _state.value.unpinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.unpinnedSortDirection.direction
                ).collect { inActiveTasks ->
                    _state.update { it.copy(inActiveTasks = inActiveTasks) }
                }
            } else {
                getAllInActiveRecentTasksUseCase.get().execute(
                    sortBy = _state.value.unpinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.unpinnedSortDirection.direction
                ).collect { inActiveTasks ->
                    _state.update { it.copy(inActiveTasks = inActiveTasks) }
                }
            }
        }
    }

    private fun getInActiveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentCategoryId = getCategoryIdUseCase.get().execute()
            if (currentCategoryId != null) {
                getInActiveTasksUseCase.get().execute(
                    categoryId = currentCategoryId,
                    sortBy = _state.value.unpinnedHomeSortParameter.parameter,
                    sortDirection = _state.value.unpinnedSortDirection.direction
                ).collect { inActiveTasks ->
                    _state.update { it.copy(inActiveTasks = inActiveTasks) }
                }
            } else {
                _state.update { it.copy(inActiveTasks = listOf()) }
            }
        }
    }

    fun onChangeGridColumnsClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(gridColumns = if (it.gridColumns == 2) 1 else 2)
            }
            withContext(Dispatchers.IO) {
                setGridColumnsUseCase.get().execute(_state.value.gridColumns)
            }
        }
    }

    fun formatDate(timestamp: Long): String {
        return formatDateUseCase.get().execute(timestamp)
    }

    fun formatTime(timestamp: Long): String {
        return formatTimeUseCase.get().execute(timestamp)
    }

    fun onTaskSelectClick(id: Long) {
        viewModelScope.launch {
            setTaskIdUseCase.get().execute(id)
            onNavigationClick(Screens.TASK_EDITOR_SCREEN)
        }
    }

    private fun clearTaskId() {
        setTaskIdUseCase.get().execute(null)
    }

    fun onNavigateToTaskEditorClick() {
        viewModelScope.launch {
            if (getCategoryIdUseCase.get().execute() != null) {
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
            Screens.SETTING_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToSettings) }
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
        handleActiveTasks()
    }

    fun onPinnedSortParameterChange(homeSortParameter: HomeSortParameter) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    pinnedHomeSortParameter = homeSortParameter
                )
            }
        }
        handleActiveTasks()
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
        handleInActiveTasks()
    }

    fun onUnPinnedSortParameterChange(homeSortParameter: HomeSortParameter) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    unpinnedHomeSortParameter = homeSortParameter
                )
            }
        }
        handleInActiveTasks()
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

    fun onToggleSearchBar() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showSearchBar = !it.showSearchBar,
                    searchTitle = ""
                )
            }
        }
    }

    fun onSetSearchTitle(searchTitle: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    searchTitle = searchTitle
                )
            }
        }
    }

    fun onToggleAllLines(taskType: TaskType) {
        viewModelScope.launch {
            _state.update {
                when (taskType) {
                    TaskType.PINNED -> it.copy(allPinnedTitleLines = !it.allPinnedTitleLines)
                    TaskType.UNPINNED -> it.copy(allUnpinnedTitleLines = !it.allUnpinnedTitleLines)
                }
            }
        }
    }
}