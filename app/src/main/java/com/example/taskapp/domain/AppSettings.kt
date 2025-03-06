package com.example.taskapp.domain

import com.example.taskapp.presentation.setting_screen.models.RecentNoteFilter

interface AppSettings {
    suspend fun setGridColumns(columnsSize: Int)
    suspend fun getGridColumns(): Int
    suspend fun setAppTheme(darkTheme: Boolean)
    suspend fun getAppTheme(): Boolean
    suspend fun setLanguageCode(languageCode: String)
    suspend fun getLanguageCode(): String
    suspend fun setRecentNoteFilter(recentNoteFilter: RecentNoteFilter)
    suspend fun getRecentNoteFilter(): RecentNoteFilter
}