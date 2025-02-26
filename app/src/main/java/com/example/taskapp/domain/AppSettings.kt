package com.example.taskapp.domain

interface AppSettings {
    suspend fun setGridColumns(columnsSize: Int)
    suspend fun getGridColumns(): Int
    suspend fun setAppTheme(darkTheme: Boolean)
    suspend fun getAppTheme(): Boolean
}