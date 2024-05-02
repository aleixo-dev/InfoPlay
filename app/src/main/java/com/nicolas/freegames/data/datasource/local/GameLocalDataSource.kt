package com.nicolas.freegames.data.datasource.local

import com.nicolas.freegames.data.local.ModelGameEntity
import kotlinx.coroutines.flow.Flow

interface GameLocalDataSource {

    suspend fun insertData(modelGameEntity: ModelGameEntity)

    suspend fun getAllData(): List<ModelGameEntity>

}