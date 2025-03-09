package com.example.taskapp.domain.usecase.settings

import com.example.taskapp.domain.AppSettings

class SetAppThemeUseCase(
    private val appSettings: AppSettings
) {
    suspend fun execute(darkTheme: Boolean) {
        appSettings.setAppTheme(darkTheme)
    }
}