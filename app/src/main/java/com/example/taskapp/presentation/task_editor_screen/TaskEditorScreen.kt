package com.example.taskapp.presentation.task_editor_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorContent
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorNavigationEvent
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorViewModel

@Composable
fun TaskEditorScreen(
    viewModel: TaskEditorViewModel,
    navigationToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                TaskEditorNavigationEvent.NavigationToHome -> navigationToHomeScreen()
            }
        }

    }
    TaskEditorContent(
        state = state,
        onSetTaskTitle = viewModel::onSetTaskTitle,
        onSetTaskDescription = viewModel::onSetTaskDescription,
        onHomeScreenNavigationClick = viewModel::onHomeScreenNavigationClick,
        onCreateTaskClick = viewModel::onCreateTaskClick,
        onToggleDropDawnMenuClick = viewModel::onToggleDropDawnMenuClick,
        onTogglePinTaskClick = viewModel::onTogglePinTaskClick
    )
}
