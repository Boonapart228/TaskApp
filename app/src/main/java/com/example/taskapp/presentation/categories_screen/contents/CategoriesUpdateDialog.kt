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
fun CategoriesUpdateDialog(
    value: String,
    onValueChange: (String) -> Unit,
    onToggleDialogClick: () -> Unit,
    updateCategory: () -> Unit,
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
            onToggleDialogClick()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    updateCategory()
                    onToggleDialogClick()
                }
            ) {
                Text(stringResource(id = R.string.update_text))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    clearCategoryTitle()
                    onToggleDialogClick()
                }
            ) {
                Text(stringResource(id = R.string.dismiss_text))
            }
        }
    )
}

@Composable
@Preview
fun CategoriesUpdateDialogPreview() {
    CategoriesUpdateDialog(
        value = "",
        onValueChange = {},
        onToggleDialogClick = {},
        updateCategory = {},
        clearCategoryTitle = {}
    )
}