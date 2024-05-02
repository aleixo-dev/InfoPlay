package com.nicolas.freegames.data.datasource.remote

import com.nicolas.freegames.models.remote.GameDetailResponse
import com.nicolas.freegames.models.remote.GameResponse

interface GameRemoteDataSource {

    suspend fun getAllGames() : GameResponse
    suspend fun getGamePerCategory(category : String) : GameResponse
    suspend fun getGameDetail(gameId : String) : GameDetailResponse
}