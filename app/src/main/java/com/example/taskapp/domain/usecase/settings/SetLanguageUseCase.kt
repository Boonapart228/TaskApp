package com.example.taskapp.domain.usecase.settings

import com.example.taskapp.domain.AppSettings
import com.example.taskapp.presentation.setting_screen.models.Language

class SetLanguageUseCase(
    private val appSettings: AppSettings
) {
    suspend fun execute(language : Language) {
        appSettings.setLanguage(language)
    }
}