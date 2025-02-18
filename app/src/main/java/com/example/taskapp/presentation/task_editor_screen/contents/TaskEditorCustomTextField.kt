package com.example.taskapp.presentation.task_editor_screen.contents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun TaskEditorCustomTextField(
    value: String,
    placeholderId: Int,
    singleLine: Boolean,
    fontSize: TextUnit,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalDimen.current.taskEditorTextFieldPaddingAll),

        placeholder = { Text(text = stringResource(id = placeholderId)) },
        textStyle = TextStyle(fontSize = fontSize),
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = MaterialTheme.colorScheme.background
        )
    )
}