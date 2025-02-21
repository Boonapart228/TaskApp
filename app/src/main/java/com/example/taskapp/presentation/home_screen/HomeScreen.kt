package com.example.taskapp.presentation.home_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.taskapp.R
import com.example.taskapp.presentation.home_screen.components.HomeContent
import com.example.taskapp.presentation.home_screen.components.HomeMessageEvent
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
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                HomeNavigationEvent.NavigationToCategories -> navigateToCategories()
                HomeNavigationEvent.NavigationToTaskEditor -> navigationToTaskEditor()
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
        formatDate = viewModel::formatDate,
        formatTime = viewModel::formatTime,
        onChangeGridColumnsClick = viewModel::onChangeGridColumnsClick,
        onTaskSelectClick = viewModel::onTaskSelectClick,
        onNavigateToTaskEditorClick = viewModel::onNavigateToTaskEditorClick,
        onPinnedDirectionChange = viewModel::onPinnedDirectionChange,
        onPinnedSortParameterChange = viewModel::onPinnedSortParameterChange,
        onTogglePinnedMenuClick = viewModel::onTogglePinnedMenuClick,
        onUnPinnedDirectionChange = viewModel::onUnPinnedDirectionChange,
        onUnPinnedSortParameterChange = viewModel::onUnPinnedSortParameterChange,
        onToggleUnPinnedMenuClick = viewModel::onToggleUnPinnedMenuClick
    )
}