package com.nicolas.freegames.data.local.datasource

import com.nicolas.freegames.data.local.dao.ModelGameDao
import com.nicolas.freegames.data.local.entities.GameEntity
import javax.inject.Inject

class GameLocalDataSourceImpl @Inject constructor(
    private val gameDao: ModelGameDao
) : GameLocalDataSource {

    override suspend fun create(gameEntity: List<GameEntity>) {
        gameDao.insert(gameEntity)
    }

    override suspend fun getAll(): List<GameEntity> {
        val entity = gameDao.getData()
        return entity
    }
}