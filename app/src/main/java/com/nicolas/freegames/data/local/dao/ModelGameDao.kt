package com.nicolas.freegames.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicolas.freegames.data.local.entities.GameEntity

@Dao
interface ModelGameDao {

    @Query("SELECT * FROM gameentity")
    suspend fun getData(): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(modelGame: List<GameEntity>)

}