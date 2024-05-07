package com.nicolas.freegames.data.repository

import com.nicolas.freegames.data.local.datasource.GameLocalDataSource
import com.nicolas.freegames.data.local.datastore.CacheTimeDataStore
import com.nicolas.freegames.data.mapper.asEntity
import com.nicolas.freegames.data.mapper.asExternalModel
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
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FreeGameRepositoryImpl @Inject constructor(
    private val remote: GameRemoteDataSource,
    private val local: GameLocalDataSource,
    private val dataStore: CacheTimeDataStore,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FreeGameRepository {

    override suspend fun getAllGames(): Flow<List<GameDomain>> = flow {

        val currentTime = System.currentTimeMillis()
        val lastUpdate = dataStore.getLastUpdateTime().getOrNull() ?: 0L

        if (currentTime - lastUpdate >= TimeUnit.MINUTES.toMillis(10)) {

            val apiResponse = remote.getAllGames()
            local.create(apiResponse.map { networkGame -> networkGame.asEntity() })

            emit(apiResponse.map { network -> network.toGameDomain() }.sortedBy { gameDomain ->
                gameDomain.publisher
            })

            dataStore.saveLastUpdateTime(currentTime)

        } else {

            val localData = local.getAll()
            emit(localData.map { entity ->
                entity.asExternalModel()
            })
        }

    }.flowOn(defaultDispatcher)

    override suspend fun getGamesPerCategory(category: String): Flow<List<GameDomain>> = flow {
        emit(remote.getGamePerCategory(category = category).map { it.toGameDomain() })
    }.flowOn(defaultDispatcher)

    override suspend fun getGameDetail(gameId: String): Flow<DetailGameDomain> = flow {
        emit(remote.getGameDetail(gameId).toGameDetailDomain())
    }.flowOn(defaultDispatcher)

}