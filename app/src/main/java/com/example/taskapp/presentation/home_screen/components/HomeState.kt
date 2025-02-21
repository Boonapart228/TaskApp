package com.example.taskapp.presentation.home_screen.components

import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.home_screen.model.SortDirection
import com.example.taskapp.presentation.home_screen.model.SortParameter
import com.example.taskapp.presentation.navigation.model.Screens

data class HomeState(
    val selectedScreen: Screens = Screens.HOME_SCREEN,
    val activeTasks: List<Task> = listOf(),
    val inActiveTasks: List<Task> = listOf(),
    val pinnedSortDirection: String = SortDirection.ASCENDING.direction,
    val pinnedSortParameter: SortParameter = SortParameter.TITLE,
    val unpinnedSortDirection: String = SortDirection.ASCENDING.direction,
    val unpinnedSortParameter: SortParameter = SortParameter.TITLE,
    val showPinnedSortDialog: Boolean = false,
    val showUnpinnedSortDialog: Boolean = false,
    val gridColumns: Int = 2
)