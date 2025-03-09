package com.example.taskapp.presentation.home_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.taskapp.R
import com.example.taskapp.presentation.home_screen.components.HomeContent
import com.example.taskapp.presentation.home_screen.components.HomeMessageEvent
import com.example.taskapp.presentation.home_screen.components.HomeNavigationEvent
import com.example.taskapp.presentation.home_screen.components.HomeViewModel


@Composable
fun HomeScreen(
    navigateToCategories: () -> Unit,
    navigationToTaskEditor: () -> Unit,
    navigateToSettings: () -> Unit,
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                HomeNavigationEvent.NavigationToCategories -> {
                    navigateToCategories()
                }

                HomeNavigationEvent.NavigationToTaskEditor -> {
                    navigationToTaskEditor()
                }

                HomeNavigationEvent.NavigationToSettings -> {
                    navigateToSettings()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.messageEvent.collect {
            when (it) {
                HomeMessageEvent.ChoseCategory -> {
                    Toast.makeText(
                        context,
                        R.string.choose_category_text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    HomeContent(
        state = state,
        onNavigationClick = viewModel::onNavigationClick,
        onChangeGridColumnsClick = viewModel::onChangeGridColumnsClick,
        onTogglePinnedMenuClick = viewModel::onTogglePinnedMenuClick,
        onToggleUnPinnedMenuClick = viewModel::onToggleUnPinnedMenuClick,
        onNavigateToTaskEditorClick = viewModel::onNavigateToTaskEditorClick,
        onToggleSearchBar = viewModel::onToggleSearchBar,
        onToggleAllTitleLines = viewModel::onToggleAllLines,
        onSetSearchTitle = viewModel::onSetSearchTitle,
        formatTime = viewModel::formatTime,
        formatDate = viewModel::formatDate,
        onTaskSelectClick = viewModel::onTaskSelectClick,
        onPinnedDirectionChange = viewModel::onPinnedDirectionChange,
        onPinnedSortParameterChange = viewModel::onPinnedSortParameterChange,
        onUnPinnedDirectionChange = viewModel::onUnPinnedDirectionChange,
        onUnPinnedSortParameterChange = viewModel::onUnPinnedSortParameterChange,
        onChangeNoteFilterType = viewModel::onChangeNoteFilterType
    )
}