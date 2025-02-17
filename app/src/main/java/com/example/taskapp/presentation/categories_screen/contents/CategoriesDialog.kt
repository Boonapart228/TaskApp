package com.example.taskapp.presentation.categories_screen.contents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R

@Composable
fun CategoriesDialog(
    value: String,
    onValueChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    clearCategoryTitle: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.Create, contentDescription = null)
        },
        title = {
            Text(text = stringResource(id = R.string.enter_category_name_text))
        },
        text = {
            OutlinedTextField(
                value = value, onValueChange = onValueChange,
                singleLine = true
            )
        },
        onDismissRequest = {
            clearCategoryTitle()
            onDismissClick()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClick()
                }
            ) {
                Text(stringResource(id = R.string.next_text))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    clearCategoryTitle()
                    onDismissClick()
                }
            ) {
                Text(stringResource(id = R.string.dismiss_text))
            }
        }
    )
}

@Composable
@Preview
fun CategoriesCreationDialogPreview() {
    CategoriesDialog(
        value = "",
        onValueChange = {},
        clearCategoryTitle = {},
        onConfirmClick = {},
        onDismissClick = {}
    )
}