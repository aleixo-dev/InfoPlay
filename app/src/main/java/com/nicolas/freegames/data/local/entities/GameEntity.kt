package com.nicolas.freegames.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "genre") val genre: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "platform") val platform: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "publisher") val publisher: String?,
    @ColumnInfo(name = "developer") val developer: String?
)