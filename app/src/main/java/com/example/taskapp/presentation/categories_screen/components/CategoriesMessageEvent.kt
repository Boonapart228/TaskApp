package com.example.taskapp.presentation.categories_screen.components

sealed class CategoriesMessageEvent {
    data object CategoryDeletionSuccess : CategoriesMessageEvent()
    data object CategoryUpdateSuccess : CategoriesMessageEvent()
    data object CategoryCreationSuccess : CategoriesMessageEvent()
    data object CategoryTitleCannotBeEmpty : CategoriesMessageEvent()
}