package com.nicolas.freegames.di

import com.nicolas.freegames.data.datasource.remote.GameRemoteDataSource
import com.nicolas.freegames.data.datasource.remote.GameRemoteDataSourceImpl
import com.nicolas.freegames.data.service.FreeGameService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideGameDataSource(service: FreeGameService): GameRemoteDataSource {
        return GameRemoteDataSourceImpl(service)
    }

}