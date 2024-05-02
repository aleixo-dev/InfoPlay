package com.nicolas.freegames.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.nicolas.freegames.data.local.datasource.GameLocalDataSource
import com.nicolas.freegames.data.mapper.toGameDetailDomain
import com.nicolas.freegames.data.mapper.toGameDomain
import com.nicolas.freegames.data.network.datasource.GameRemoteDataSource
import com.nicolas.freegames.model.DetailGameDomain
import com.nicolas.freegames.model.GameDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class FreeGameRepositoryImpl @Inject constructor(
    private val remote: GameRemoteDataSource,
    private val local: GameLocalDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : FreeGameRepository {

    override suspend fun getAllGames(): Flow<List<GameDomain>> = flow {

        val response = remote.getAllGames()
        emit(response.map { it.toGameDomain() }.sortedBy { it.publisher })

    }.flowOn(defaultDispatcher)

    override suspend fun getGamesPerCategory(category: String): Flow<List<GameDomain>> = flow {
        emit(remote.getGamePerCategory(category = category).map { it.toGameDomain() })
    }.flowOn(defaultDispatcher)

    override suspend fun getGameDetail(gameId: String): Flow<DetailGameDomain> = flow {
        emit(remote.getGameDetail(gameId).toGameDetailDomain())
    }.flowOn(defaultDispatcher)

}