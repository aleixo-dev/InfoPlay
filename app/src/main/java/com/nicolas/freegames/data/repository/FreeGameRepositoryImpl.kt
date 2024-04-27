package com.nicolas.freegames.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nicolas.freegames.data.local.ModelGameDao
import com.nicolas.freegames.data.mapper.toData
import com.nicolas.freegames.data.mapper.toDomain
import com.nicolas.freegames.data.mapper.toEntity
import com.nicolas.freegames.data.mapper.toGameDetail
import com.nicolas.freegames.data.service.FreeGameService
import com.nicolas.freegames.models.domain.DetailGame
import com.nicolas.freegames.models.domain.ModelGame
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val DATA_STORE_NAME = "config_cache"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class FreeGameRepositoryImpl @Inject constructor(
    private val freeGameService: FreeGameService,
    private val database: ModelGameDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val context: Context
) : FreeGameRepository {

    companion object {
        val CACHE = longPreferencesKey("CACHE")
        val INSERT_LOCAL_DATA = booleanPreferencesKey("INSERT_LOCAL_DATA")
        const val TIME_CACHE_VALID = 86400000L
    }

    override suspend fun getAllGames(): Flow<List<ModelGame>> = flow {
        emit(freeGameService.getAllGames().toData().sortedBy {
            it.publisher
        })
    }

    override suspend fun getGamesPerCategory(category: String): Flow<List<ModelGame>> = flow {
        emit(freeGameService.getGamePerCategory(category = category).toData())
    }

    override suspend fun getGameDetail(gameId: String): Flow<DetailGame> = flow {
        emit(freeGameService.getGameDetail(gameId).toGameDetail())
    }

    override suspend fun saveLocalModelGame(modelGame: ModelGame) = withContext(defaultDispatcher) {
        val entity = modelGame.toEntity()
        database.insertAllData(modelGame = entity)
    }

    override suspend fun getAllModelGameLocal(): Flow<List<ModelGame>> = flow {
        val local = database.getAll().map { it.toDomain() }
        emit(local.sortedBy { it.publisher })
    }.flowOn(defaultDispatcher)

    override suspend fun saveCacheTime() {
        context.dataStore.edit { currentCache ->
            currentCache[CACHE] = System.currentTimeMillis()
        }
    }

    override suspend fun isCacheValid(): Boolean {
        val lastCacheTimePref: Flow<Long> = context.dataStore.data.map { prefs ->
            prefs[CACHE] ?: 0L
        }
        val currentTime = System.currentTimeMillis()
        var lastCacheTime = 0L
        lastCacheTimePref.collect { lastCacheTime = it }
        return isCacheSavedMoreThanOneDay(currentTime, lastCacheTime)
    }

    override suspend fun hasInsertData(): Boolean {
        var data = false
        context.dataStore.data.map { prefs ->
            prefs[INSERT_LOCAL_DATA] ?: false
        }.collect {
            data = it
        }
        return data
    }

    override suspend fun setInsertDate() {
        context.dataStore.edit { prefs ->
            prefs[INSERT_LOCAL_DATA] = true
        }
    }

    private fun isCacheSavedMoreThanOneDay(now: Long, lastCacheTime: Long) =
        now - lastCacheTime >= TIME_CACHE_VALID
}