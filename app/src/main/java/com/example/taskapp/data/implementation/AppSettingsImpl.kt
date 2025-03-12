package com.example.taskapp.data.implementation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskapp.domain.AppSettings
import com.example.taskapp.presentation.setting_screen.models.Language
import com.example.taskapp.presentation.setting_screen.models.RecentNoteFilter
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preference_name")

class AppSettingsImpl(
    private val context: Context
) : AppSettings {
    private val GRID_COLUMNS = intPreferencesKey("grid_columns")
    private val APP_THEME = booleanPreferencesKey("app_theme")
    private val LANGUAGE_CODE = stringPreferencesKey("language_code")
    private val RECENT_FILTER = stringPreferencesKey("recent_filter")

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

    override suspend fun setLanguage(language: Language) {
        context.dataStore.edit { languageSetting ->
            languageSetting[LANGUAGE_CODE] = language.name
        }

    }

    override suspend fun getLanguage(): Language {
        val preferences = context.dataStore.data.first()
        val language = preferences[LANGUAGE_CODE] ?: Language.ENGLISH.name
        return Language.valueOf(language)
    }

    override suspend fun setRecentNoteFilter(recentNoteFilter: RecentNoteFilter) {
        context.dataStore.edit { recentFilter ->
            recentFilter[RECENT_FILTER] = recentNoteFilter.name
        }
    }

    override suspend fun getRecentNoteFilter(): RecentNoteFilter {
        val preferences = context.dataStore.data.first()
        val typeName = preferences[RECENT_FILTER] ?: RecentNoteFilter.CURRENT.name
        return RecentNoteFilter.valueOf(typeName)
    }


}