package com.nicolas.freegames.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userScreen")
        val PREFS_KEY = booleanPreferencesKey("has_accessed_screen")
    }

    val hasAccessedScreen: Flow<Boolean> =context.dataStore.data.map { preferences ->
            preferences[PREFS_KEY] ?: false
        }

    suspend fun setHasAccessedScreen(hasAccessed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PREFS_KEY] = hasAccessed
        }
    }
}