package com.example.taskapp.presentation.task_editor_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.presentation.task_editor_screen.contents.TaskEditorTopBar
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun TaskEditorContent(
    state: TaskEditorState,
    onSetTaskTitle: (String) -> Unit,
    onSetTaskDescription: (String) -> Unit,
    onHomeScreenNavigationClick: (Screens) -> Unit
) {
    Scaffold(topBar = {
        TaskEditorTopBar(
            onHomeScreenNavigationClick = onHomeScreenNavigationClick
        )
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Done, contentDescription = null,
                    modifier = Modifier.size(LocalDimen.current.homeIconFABSize)
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = LocalDimen.current.taskEditorColumnTopPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.title, onValueChange = onSetTaskTitle,
                label = { Text(text = stringResource(id = R.string.enter_title_text)) },
                textStyle = TextStyle(fontSize = LocalDimen.current.taskEditorTaskTitleSize),
                singleLine = true
            )
            TextField(
                value = state.description,
                onValueChange = onSetTaskDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LocalDimen.current.taskEditorTextFieldPaddingAll),
                textStyle = TextStyle(

                    fontSize = LocalDimen.current.taskEditorTaskDescriptionSize
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = MaterialTheme.colorScheme.background
                )
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
        onSetTaskTitle = {}
    )
}

