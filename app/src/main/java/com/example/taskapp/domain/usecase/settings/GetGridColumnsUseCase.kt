package com.example.taskapp.domain.usecase.settings

import com.example.taskapp.domain.AppSettings

class GetGridColumnsUseCase(
    private val appSettings: AppSettings
) {
    suspend fun execute() : Int = appSettings.getGridColumns()
}