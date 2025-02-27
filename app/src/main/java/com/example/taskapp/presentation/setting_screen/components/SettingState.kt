package com.example.taskapp.presentation.setting_screen.components

data class SettingState(
    val darkTheme: Boolean = false,
    val expandedLanguageMenu: Boolean = false,
    val languageCode: String = ""
)
