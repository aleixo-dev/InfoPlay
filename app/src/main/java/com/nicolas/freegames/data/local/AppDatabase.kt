package com.nicolas.freegames.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ModelGameEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun modelGameDao(): ModelGameDao
}