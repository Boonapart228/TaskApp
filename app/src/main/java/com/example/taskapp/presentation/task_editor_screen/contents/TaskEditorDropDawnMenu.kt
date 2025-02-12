package com.example.taskapp.presentation.task_editor_screen.contents

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.task_editor_screen.model.TaskEditorDropDawnItems

@Composable
fun TaskEditorDropDawnMenu(
    expanded: Boolean,
    pin: Boolean,
    onToggleDropDawnMenuClick: () -> Unit,
    onTogglePinTaskClick: () -> Unit,
    onDeleteTaskClick: () -> Unit,
    onEditColorClick: () -> Unit,
) {
    Box {
        IconButton(onClick = onToggleDropDawnMenuClick) {
            Icon(Icons.Default.MoreVert, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onToggleDropDawnMenuClick
        ) {
            TaskEditorDropDawnItems.entries.forEach { item ->
                val shouldShowItem = when {
                    pin -> item != TaskEditorDropDawnItems.PIN
                    else -> item != TaskEditorDropDawnItems.UN_PIN
                }

                if (shouldShowItem) {
                    if (item != TaskEditorDropDawnItems.PIN && item != TaskEditorDropDawnItems.UN_PIN) {
                        HorizontalDivider()
                    }
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = item.textId)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = item.iconId),
                                contentDescription = null
                            )
                        },
                        onClick = {
                            when (item) {
                                TaskEditorDropDawnItems.PIN -> onTogglePinTaskClick()
                                TaskEditorDropDawnItems.UN_PIN -> onTogglePinTaskClick()
                                TaskEditorDropDawnItems.COLOR -> onEditColorClick()
                                TaskEditorDropDawnItems.DELETE -> onDeleteTaskClick()
                            }
                            onToggleDropDawnMenuClick()
                        }
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun TaskEditorDropDawnMenuPreview() {
    TaskEditorDropDawnMenu(
        expanded = false,
        pin = false,
        onToggleDropDawnMenuClick = {},
        onTogglePinTaskClick = {},
        onDeleteTaskClick = {}) {

    }
}