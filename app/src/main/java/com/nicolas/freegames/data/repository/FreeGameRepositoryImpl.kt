package com.nicolas.freegames.data.repository

import com.nicolas.freegames.data.datasource.remote.GameRemoteDataSource
import com.nicolas.freegames.data.mapper.toData
import com.nicolas.freegames.data.mapper.toGameDetail
import com.nicolas.freegames.models.domain.DetailGame
import com.nicolas.freegames.models.domain.ModelGame
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FreeGameRepositoryImpl @Inject constructor(
    private val gameRemoteDataSource: GameRemoteDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : FreeGameRepository {

    override suspend fun getAllGames(): Flow<List<ModelGame>> = flow {
        emit(gameRemoteDataSource.getAllGames().toData().sortedBy {
            it.publisher
        })
    }.flowOn(defaultDispatcher)

    override suspend fun getGamesPerCategory(category: String): Flow<List<ModelGame>> = flow {
        emit(gameRemoteDataSource.getGamePerCategory(category = category).toData())
    }.flowOn(defaultDispatcher)

    override suspend fun getGameDetail(gameId: String): Flow<DetailGame> = flow {
        emit(gameRemoteDataSource.getGameDetail(gameId).toGameDetail())
    }.flowOn(defaultDispatcher)

}