package com.example.taskapp.presentation.task_editor_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.taskapp.R
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorContent
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorMessageEvent
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorNavigationEvent
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorViewModel

@Composable
fun TaskEditorScreen(
    viewModel: TaskEditorViewModel,
    navigationToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                TaskEditorNavigationEvent.NavigationToHome -> navigationToHomeScreen()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.messageEvent.collect {
            when (it) {
                TaskEditorMessageEvent.TaskCreationSuccess -> {
                    Toast.makeText(
                        context,
                        R.string.success_create_text,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                TaskEditorMessageEvent.TaskUpdateSuccess -> {
                    Toast.makeText(
                        context,
                        R.string.success_update_text,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
    TaskEditorContent(
        state = state,
        onSetTaskTitle = viewModel::onSetTaskTitle,
        onSetTaskDescription = viewModel::onSetTaskDescription,
        onHomeScreenNavigationClick = viewModel::onHomeScreenNavigationClick,
        onEditTaskClick = viewModel::onEditTaskClick,
        onToggleDropDawnMenuClick = viewModel::onToggleDropDawnMenuClick,
        onTogglePinTaskClick = viewModel::onTogglePinTaskClick
    )
}
