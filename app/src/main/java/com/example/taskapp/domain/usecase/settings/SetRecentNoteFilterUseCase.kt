package com.example.taskapp.domain.usecase.settings

import com.example.taskapp.domain.AppSettings
import com.example.taskapp.presentation.setting_screen.models.RecentNoteFilter

class SetRecentNoteFilterUseCase(
    private val appSettings: AppSettings
) {
    suspend fun execute(recentNoteFilter : RecentNoteFilter){
        appSettings.setRecentNoteFilter(recentNoteFilter)
    }
}