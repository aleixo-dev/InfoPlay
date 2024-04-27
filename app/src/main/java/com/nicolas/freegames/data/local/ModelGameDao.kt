package com.nicolas.freegames.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ModelGameDao {

    @Query("SELECT * FROM modelgameentity")
    fun getAll(): List<ModelGameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(modelGame: ModelGameEntity)

}