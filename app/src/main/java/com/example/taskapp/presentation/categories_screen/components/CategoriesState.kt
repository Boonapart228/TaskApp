package com.example.taskapp.presentation.categories_screen.components

import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.navigation.model.Screens

data class CategoriesState(
    val selectedScreen: Screens = Screens.CATEGORIES_SCREEN,
    val showDialogCreateCategory: Boolean = false,
    val showDialogUpdateCategory: Boolean = false,
    val oldCategoryTitle: String = "",
    val categoryTitle: String = "",
    val taskManagerItems: MutableMap<String, MutableList<Task>> = mutableMapOf(),
    val expandedMenu: Boolean = false,
)