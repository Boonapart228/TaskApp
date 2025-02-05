package com.example.taskapp.presentation.home_screen.components

sealed class HomeNavigationEvent {
    data object NavigationToCategories : HomeNavigationEvent()
    data object NavigationToTaskEditor : HomeNavigationEvent()
}