package com.example.taskapp.presentation.categories_screen.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.taskapp.R

enum class CategoriesDropDawnItems(val textId: Int, val icon: ImageVector) {
    EDIT(textId = R.string.edit_text, icon = Icons.Default.Edit),
    DELETE(textId = R.string.delete_text, icon = Icons.Default.Delete)
}