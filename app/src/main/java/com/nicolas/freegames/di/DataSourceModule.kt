package com.nicolas.freegames.di

import com.nicolas.freegames.data.local.datasource.GameLocalDataSource
import com.nicolas.freegames.data.local.datasource.GameLocalDataSourceImpl
import com.nicolas.freegames.data.network.datasource.GameRemoteDataSource
import com.nicolas.freegames.data.network.datasource.GameRemoteDataSourceImpl
import com.nicolas.freegames.data.local.dao.ModelGameDao
import com.nicolas.freegames.data.network.FreeGameService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideGameRemoteDataSource(service: FreeGameService): GameRemoteDataSource {
        return GameRemoteDataSourceImpl(service)
    }

    @Provides
    fun provideGameLocalDataSource(dao : ModelGameDao) : GameLocalDataSource {
        return GameLocalDataSourceImpl(dao)
    }

}