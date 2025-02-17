package com.example.taskapp.presentation.task_editor_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.presentation.task_editor_screen.contents.TaskEditorCustomTextField
import com.example.taskapp.presentation.task_editor_screen.contents.TaskEditorTopBar
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun TaskEditorContent(
    state: TaskEditorState,
    onEditTaskClick: () -> Unit,
    onToggleDropDawnMenuClick: () -> Unit,
    onTogglePinTaskClick: () -> Unit,
    onSetTaskTitle: (String) -> Unit,
    onSetTaskDescription: (String) -> Unit,
    onHomeScreenNavigationClick: (Screens) -> Unit
) {
    Scaffold(topBar = {
        TaskEditorTopBar(
            expanded = state.expanded,
            pin = state.pin,
            fieldsChanged = state.fieldsChanged,
            onDeleteTaskClick = {},
            onTogglePinTaskClick = onTogglePinTaskClick,
            onToggleDropDawnMenuClick = onToggleDropDawnMenuClick,
            onHomeScreenNavigationClick = onHomeScreenNavigationClick,
            onEditTaskClick = onEditTaskClick
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TaskEditorCustomTextField(
                value = state.title,
                onValueChange = { newTitle -> onSetTaskTitle(newTitle) },
                labelId = R.string.enter_title_text,
                fontSize = LocalDimen.current.taskEditorTaskTitleSize,
                singleLine = true
            )
            TaskEditorCustomTextField(
                value = state.description,
                onValueChange = { newDescription -> onSetTaskDescription(newDescription) },
                labelId = R.string.enter_description_text,
                fontSize = LocalDimen.current.taskEditorTaskDescriptionSize,
                singleLine = false
            )
        }

    }
}

@Composable
@Preview
fun TaskEditorContentPreview() {
    TaskEditorContent(
        state = TaskEditorState(),
        onHomeScreenNavigationClick = {},
        onSetTaskDescription = {},
        onSetTaskTitle = {},
        onEditTaskClick = {},
        onToggleDropDawnMenuClick = {},
        onTogglePinTaskClick = {}
    )
}

