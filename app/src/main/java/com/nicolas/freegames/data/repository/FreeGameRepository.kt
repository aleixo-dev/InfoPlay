package com.nicolas.freegames.data.repository

import com.nicolas.freegames.models.domain.DetailGame
import com.nicolas.freegames.models.domain.ModelGame
import kotlinx.coroutines.flow.Flow

interface FreeGameRepository {

    suspend fun getAllGames() : Flow<List<ModelGame>>
    suspend fun getGamesPerCategory(category : String) : Flow<List<ModelGame>>
    suspend fun getGameDetail(gameId : String) : Flow<DetailGame>

    suspend fun saveLocalModelGame(modelGame: ModelGame)

    suspend fun getAllModelGameLocal() : Flow<List<ModelGame>>

    suspend fun saveCacheTime()
    suspend fun isCacheValid() : Boolean

    suspend fun hasInsertData() : Boolean
    suspend fun setInsertDate()

}