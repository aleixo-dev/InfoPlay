package com.nicolas.freegames.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class CacheTimeDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : CacheTimeDataStore {

    override suspend fun getLastUpdateTime(): Result<Long> {
        return Result.runCatching {
            val flow = dataStore.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[KEY_NAME] ?: 0L
            }
            val value = flow.firstOrNull() ?: 0L
            value
        }
    }

    override suspend fun saveLastUpdateTime(timestamp: Long) {

        dataStore.edit { preferences ->
            preferences[KEY_NAME] = timestamp
        }

    }

    companion object {
        val KEY_NAME = longPreferencesKey("cached")
    }

}
