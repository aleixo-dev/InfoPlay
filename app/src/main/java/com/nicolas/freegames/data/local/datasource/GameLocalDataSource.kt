package com.nicolas.freegames.data.local.datasource

import com.nicolas.freegames.data.local.entities.GameEntity

interface GameLocalDataSource {

    suspend fun create(gameEntity: List<GameEntity>)

    suspend fun getAll(): List<GameEntity>


}