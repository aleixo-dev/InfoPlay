package com.nicolas.freegames.data.network

import com.nicolas.freegames.data.network.models.NetworkGameDetail
import com.nicolas.freegames.data.network.models.NetworkGame
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeGameService {

    @GET("games")
    suspend fun getAllGames() : List<NetworkGame>

    @GET("games")
    suspend fun getGamePerCategory(
        @Query("category") category : String
    ) : List<NetworkGame>

    @GET("game")
    suspend fun getGameDetail(
        @Query("id") gameId : String
    ) : NetworkGameDetail
}