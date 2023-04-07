package com.nicolas.freegames.data.service

import com.nicolas.freegames.models.remote.GameDetailResponse
import com.nicolas.freegames.models.remote.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeGameService {

    @GET("games")
    suspend fun getAllGames() : GameResponse

    @GET("games")
    suspend fun getGamePerCategory(
        @Query("category") category : String
    ) : GameResponse

    @GET("game")
    suspend fun getGameDetail(
        @Query("id") gameId : String
    ) : GameDetailResponse
}