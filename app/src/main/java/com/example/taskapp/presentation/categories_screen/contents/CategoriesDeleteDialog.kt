package com.example.taskapp.presentation.categories_screen.contents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R

@Composable
fun CategoriesDeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
        },
        title = {
            Text(text = stringResource(id = R.string.confirm_deletion_text))
        },
        text = {
            Text(text = stringResource(id = R.string.delete_category_confirmation))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(id = R.string.confirm_text))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.dismiss_text))
            }
        }
    )
}

@Composable
@Preview
fun CategoriesDeleteDialogPreview() {
    CategoriesDeleteDialog(
        onConfirmation = {},
        onDismissRequest = {}
    )
}