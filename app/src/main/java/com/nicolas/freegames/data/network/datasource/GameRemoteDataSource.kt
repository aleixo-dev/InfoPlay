package com.nicolas.freegames.data.network.datasource

import com.nicolas.freegames.data.network.models.NetworkGameDetail
import com.nicolas.freegames.data.network.models.NetworkGame

interface GameRemoteDataSource {

    suspend fun getAllGames() : List<NetworkGame>
    suspend fun getGamePerCategory(category : String) : List<NetworkGame>
    suspend fun getGameDetail(gameId : String) : NetworkGameDetail
}