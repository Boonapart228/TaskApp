package com.example.taskapp.presentation.categories_screen.components

import com.example.taskapp.domain.model.Category
import com.example.taskapp.presentation.navigation.model.Screens

data class CategoriesState(
    val selectedScreen: Screens = Screens.CATEGORIES_SCREEN,
    val showDialogCreateCategory: Boolean = false,
    val showDialogUpdateCategory: Boolean = false,
    val categoryTitle: String = "",
    val expandedMenu: Boolean = false,
    val categories: List<Category> = listOf(),
    val currentCategory: Category? = null
)