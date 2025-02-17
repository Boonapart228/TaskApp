package com.example.taskapp.data.implementation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskapp.domain.CategoryIdStorage
import kotlinx.coroutines.flow.first

private val Context.categoryIdStorage: DataStore<Preferences> by preferencesDataStore(name = "category_storage")

class CategoryIdStorageImpl(
    private val context: Context
) : CategoryIdStorage {

    private val CATEGORY_ID = longPreferencesKey("category_id")

    override suspend fun getId(): Long? {
        val preferences = context.categoryIdStorage.data.first()
        return preferences[CATEGORY_ID]
    }

    override suspend fun setId(id: Long) {
        context.categoryIdStorage.edit { idPreference ->
            idPreference[CATEGORY_ID] = id
        }
    }
}