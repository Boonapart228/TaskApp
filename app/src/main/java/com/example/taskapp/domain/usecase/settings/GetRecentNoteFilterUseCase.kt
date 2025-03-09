package com.example.taskapp.domain.usecase.settings

import com.example.taskapp.domain.AppSettings
import com.example.taskapp.presentation.setting_screen.models.RecentNoteFilter

class GetRecentNoteFilterUseCase(
    private val appSettings: AppSettings
) {
    suspend fun execute(): RecentNoteFilter = appSettings.getRecentNoteFilter()
}