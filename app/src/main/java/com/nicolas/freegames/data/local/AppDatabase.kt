package com.nicolas.freegames.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicolas.freegames.data.local.dao.ModelGameDao
import com.nicolas.freegames.data.local.entities.GameEntity

@Database(entities = [GameEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun modelGameDao(): ModelGameDao
}