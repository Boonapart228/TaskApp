package com.example.taskapp.presentation.setting_screen.components

sealed class SettingNavigationEvent {
    data object NavigationToHome : SettingNavigationEvent()
}