package com.example.taskapp.presentation.categories_screen.components

import com.example.taskapp.domain.constants.ColorItems
import com.example.taskapp.domain.model.Category
import com.example.taskapp.domain.model.CategoryTaskManager
import com.example.taskapp.presentation.categories_screen.model.CategoryOperation
import com.example.taskapp.presentation.navigation.model.Screens

data class CategoriesState(
    val selectedScreen: Screens = Screens.CATEGORIES_SCREEN,
    val showDialogCategory: Boolean = false,
    val showDialogColorPicker: Boolean = false,
    val categoryTitle: String = "",
    val expandedMenu: Boolean = false,
    val categories: List<CategoryTaskManager> = listOf(),
    val currentCategory: Category? = null,
    val hexColorCode: String = ColorItems.entries.random().hexColorCode,
    val categoryOperation: CategoryOperation = CategoryOperation.CREATE,
    val showDialogDeleteCategory: Boolean = false,
)