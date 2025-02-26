package com.example.taskapp.data.implementation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskapp.domain.AppSettings
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preference_name")

class AppSettingsImpl(
    private val context: Context
) : AppSettings {
    private val GRID_COLUMNS = intPreferencesKey("grid_columns")
    private val APP_THEME = booleanPreferencesKey("app_theme")

    override suspend fun getGridColumns(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[GRID_COLUMNS] ?: 2
    }

    override suspend fun setGridColumns(columnsSize: Int) {
        context.dataStore.edit { columns ->
            columns[GRID_COLUMNS] = columnsSize
        }
    }

    override suspend fun setAppTheme(darkTheme: Boolean) {
        context.dataStore.edit { theme ->
            theme[APP_THEME] = darkTheme
        }
    }

    override suspend fun getAppTheme(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[APP_THEME] ?: false
    }
}