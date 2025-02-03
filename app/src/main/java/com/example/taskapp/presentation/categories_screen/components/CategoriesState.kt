package com.example.taskapp.presentation.categories_screen.components

import com.example.taskapp.presentation.navigation.model.Screens

data class CategoriesState(
    val selectedScreen: Screens = Screens.CATEGORIES_SCREEN,
    val showDialogCreateCategory: Boolean = false,
    val categoriesTitle: String = "",
    val listCategory: List<String> = listOf()
)