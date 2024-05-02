package com.nicolas.freegames.data.datasource.local

import com.nicolas.freegames.data.local.ModelGameDao
import com.nicolas.freegames.data.local.ModelGameEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GameLocalDataSourceImpl(
    private val gameDao: ModelGameDao,
    private val dispatcher: CoroutineDispatcher
) : GameLocalDataSource {

    override suspend fun insertData(modelGameEntity: ModelGameEntity) {
        withContext(dispatcher) {
            gameDao.insert(modelGameEntity)
        }
    }

    override suspend fun getAllData() = withContext(dispatcher) {
        gameDao.getData()
    }
}