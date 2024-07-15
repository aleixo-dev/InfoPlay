package com.nicolas.freegames.data.local.datastore

interface CacheTimeDataStore {

    suspend fun getLastUpdateTime(): Result<Long>
    suspend fun saveLastUpdateTime(timestamp: Long)
}