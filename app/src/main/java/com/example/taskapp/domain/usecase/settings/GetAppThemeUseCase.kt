package com.example.taskapp.domain.usecase.settings

import com.example.taskapp.domain.AppSettings

class GetAppThemeUseCase(
    private val appSettings: AppSettings
) {
    suspend fun execute(): Boolean = appSettings.getAppTheme()

}