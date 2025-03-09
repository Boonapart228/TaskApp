package com.example.taskapp.domain

import com.example.taskapp.presentation.setting_screen.models.Language
import com.example.taskapp.presentation.setting_screen.models.RecentNoteFilter

interface AppSettings {
    suspend fun setGridColumns(columnsSize: Int)
    suspend fun getGridColumns(): Int
    suspend fun setAppTheme(darkTheme: Boolean)
    suspend fun getAppTheme(): Boolean
    suspend fun setLanguage(language: Language)
    suspend fun getLanguage(): Language
    suspend fun setRecentNoteFilter(recentNoteFilter: RecentNoteFilter)
    suspend fun getRecentNoteFilter(): RecentNoteFilter
}