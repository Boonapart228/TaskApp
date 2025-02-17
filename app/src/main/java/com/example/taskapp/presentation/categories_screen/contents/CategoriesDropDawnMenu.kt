package com.example.taskapp.presentation.categories_screen.contents

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.categories_screen.model.CategoriesDropDawnItems

@Composable
fun CategoriesDropDawnMenu(
    onDeleteCategory: () -> Unit,
    onEditCategory: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert, contentDescription = null,
                tint = Color.Black
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = !expanded }
        ) {
            CategoriesDropDawnItems.entries.forEachIndexed { index, item ->
                if (index != 0) {
                    HorizontalDivider()
                }
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = item.textId)) },
                    leadingIcon = { Icon(imageVector = item.icon, contentDescription = null) },
                    onClick = {
                        when (item) {
                            CategoriesDropDawnItems.EDIT -> onEditCategory()
                            CategoriesDropDawnItems.DELETE -> onDeleteCategory()
                        }
                        expanded = !expanded
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun CategoriesDropDawnMenuPreview() {
    CategoriesDropDawnMenu(
        onDeleteCategory = {},
        onEditCategory = {}
    )
}