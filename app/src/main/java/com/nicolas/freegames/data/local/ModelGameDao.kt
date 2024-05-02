package com.nicolas.freegames.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ModelGameDao {

    @Query("SELECT * FROM modelgameentity")
    suspend fun getData(): List<ModelGameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(modelGame: ModelGameEntity)

}