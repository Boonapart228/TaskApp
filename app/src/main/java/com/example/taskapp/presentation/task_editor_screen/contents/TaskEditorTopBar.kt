package com.example.taskapp.presentation.task_editor_screen.contents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.presentation.navigation.model.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorTopBar(
    expanded: Boolean,
    pin: Boolean,
    onHomeScreenNavigationClick: (Screens) -> Unit,
    onToggleDropDawnMenuClick: () -> Unit,
    onTogglePinTaskClick: () -> Unit,
    onDeleteTaskClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        title = {
            Text(
                stringResource(id = R.string.task_editor_title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { onHomeScreenNavigationClick(Screens.HOME_SCREEN) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            TaskEditorDropDawnMenu(
                expanded = expanded,
                pin = pin,
                onToggleDropDawnMenuClick = onToggleDropDawnMenuClick,
                onTogglePinTaskClick = onTogglePinTaskClick,
                onDeleteTaskClick = onDeleteTaskClick
            ) {

            }
        }

    )
}


@Composable
@Preview
fun TaskEditorTopBarPreview() {
    TaskEditorTopBar(
        onHomeScreenNavigationClick = {},
        expanded = false,
        pin = false,
        onToggleDropDawnMenuClick = { /*TODO*/ },
        onTogglePinTaskClick = { /*TODO*/ },
        onDeleteTaskClick = { /*TODO*/ }
    )
}
