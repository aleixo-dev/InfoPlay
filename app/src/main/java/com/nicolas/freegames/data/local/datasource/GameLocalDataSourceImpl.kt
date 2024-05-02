package com.nicolas.freegames.data.local.datasource

import com.nicolas.freegames.data.local.dao.ModelGameDao
import com.nicolas.freegames.data.local.entities.GameEntity

class GameLocalDataSourceImpl(private val gameDao: ModelGameDao) : GameLocalDataSource {

    override suspend fun create(gameEntity: List<GameEntity>) = gameDao.insert(gameEntity)

    override suspend fun getAll() = gameDao.getData()
}