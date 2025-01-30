package com.example.taskapp.presentation.categories_screen.components

sealed class CategoriesNavigationEvent {
    data object NavigationToHome : CategoriesNavigationEvent()
}