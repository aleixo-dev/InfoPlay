package com.nicolas.freegames.data.repository

import com.nicolas.freegames.model.DetailGameDomain
import com.nicolas.freegames.model.GameDomain
import kotlinx.coroutines.flow.Flow

interface FreeGameRepository {

    suspend fun getAllGames() : Flow<List<GameDomain>>
    suspend fun getGamesPerCategory(category : String) : Flow<List<GameDomain>>
    suspend fun getGameDetail(gameId : String) : Flow<DetailGameDomain>


}