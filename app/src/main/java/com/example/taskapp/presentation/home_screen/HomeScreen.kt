package com.example.taskapp.presentation.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.taskapp.presentation.home_screen.components.HomeContent
import com.example.taskapp.presentation.home_screen.components.HomeNavigationEvent
import com.example.taskapp.presentation.home_screen.components.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToCategories: () -> Unit,
    navigationToTaskEditor: () -> Unit,
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                HomeNavigationEvent.NavigationToCategories -> navigateToCategories()
                HomeNavigationEvent.NavigationToTaskEditor -> navigationToTaskEditor()
            }
        }
    }
    HomeContent(
        state = state,
        onNavigationClick = viewModel::onNavigationClick,
        formatDate = viewModel::formatDate,
        formatTime = viewModel::formatTime
    )
}