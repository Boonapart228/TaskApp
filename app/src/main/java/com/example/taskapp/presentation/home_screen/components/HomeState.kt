package com.example.taskapp.presentation.home_screen.components

import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.constants.SortDirection
import com.example.taskapp.presentation.home_screen.model.HomeSortParameter
import com.example.taskapp.presentation.home_screen.model.NotesFilterType
import com.example.taskapp.presentation.navigation.model.Screens

data class HomeState(
    val selectedScreen: Screens = Screens.HOME_SCREEN,
    val activeTasks: List<Task> = listOf(),
    val inActiveTasks: List<Task> = listOf(),
    val pinnedSortDirection: SortDirection = SortDirection.ASCENDING,
    val pinnedHomeSortParameter: HomeSortParameter = HomeSortParameter.TITLE,
    val unpinnedSortDirection: SortDirection = SortDirection.ASCENDING,
    val unpinnedHomeSortParameter: HomeSortParameter = HomeSortParameter.TITLE,
    val showPinnedSortDialog: Boolean = false,
    val showUnpinnedSortDialog: Boolean = false,
    val gridColumns: Int = 2,
    val notesFilterType: NotesFilterType = NotesFilterType.ALL
)