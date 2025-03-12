package com.example.taskapp.domain.usecase.settings

import com.example.taskapp.domain.AppSettings

class SetGridColumnsUseCase(
    private val appSettings: AppSettings
) {
    suspend fun execute(columnsSize : Int) {
        appSettings.setGridColumns(columnsSize)
    }
}