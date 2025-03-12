package com.example.taskapp.presentation.setting_screen.components

import com.example.taskapp.R

data class SettingState(
    val darkTheme: Boolean = false,
    val expandedLanguageMenu: Boolean = false,
    val expandedRecentNoteMenu: Boolean = false,
    val recentNoteFilterTextId: Int = R.string.all_note_filter,
    val languageCode: String = "",
    val languageTextId: Int = R.string.english_language_text
)
