package com.example.taskapp.presentation.categories_screen.components

import com.example.taskapp.domain.model.Category
import com.example.taskapp.presentation.categories_screen.model.CategoriesColorItems
import com.example.taskapp.presentation.categories_screen.model.CategoryOperation
import com.example.taskapp.presentation.navigation.model.Screens

data class CategoriesState(
    val selectedScreen: Screens = Screens.CATEGORIES_SCREEN,
    val showDialogCategory: Boolean = false,
    val showDialogColorPicker: Boolean = false,
    val categoryTitle: String = "",
    val expandedMenu: Boolean = false,
    val categories: List<Category> = listOf(),
    val currentCategory: Category? = null,
    val hexColorCode: String = CategoriesColorItems.entries.random().hexColorCode,
    val categoryOperation: CategoryOperation = CategoryOperation.CREATE
)