package com.nicolas.freegames.data.network.datasource

import com.nicolas.freegames.data.network.FreeGameService

class GameRemoteDataSourceImpl(
    private val service: FreeGameService
) : GameRemoteDataSource {

    override suspend fun getAllGames() = service.getAllGames()

    override suspend fun getGamePerCategory(category: String) = service.getGamePerCategory(category)

    override suspend fun getGameDetail(gameId: String) = service.getGameDetail(gameId)

}