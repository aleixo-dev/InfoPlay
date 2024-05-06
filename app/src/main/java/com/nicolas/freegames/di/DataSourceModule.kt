package com.nicolas.freegames.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.nicolas.freegames.data.local.dao.ModelGameDao
import com.nicolas.freegames.data.local.datasource.GameLocalDataSource
import com.nicolas.freegames.data.local.datasource.GameLocalDataSourceImpl
import com.nicolas.freegames.data.local.datastore.CacheTimeDataStore
import com.nicolas.freegames.data.local.datastore.CacheTimeDataStoreImpl
import com.nicolas.freegames.data.network.FreeGameService
import com.nicolas.freegames.data.network.datasource.GameRemoteDataSource
import com.nicolas.freegames.data.network.datasource.GameRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cached_time")

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideDataStoreCacheTime(cacheTimeDataStore: CacheTimeDataStoreImpl): CacheTimeDataStore

    companion object {

        @Provides
        fun provideGameRemoteDataSource(service: FreeGameService): GameRemoteDataSource {
            return GameRemoteDataSourceImpl(service)
        }

        @Provides
        fun provideGameLocalDataSource(dao: ModelGameDao): GameLocalDataSource {
            return GameLocalDataSourceImpl(dao)
        }

        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.dataStore
        }
    }

}