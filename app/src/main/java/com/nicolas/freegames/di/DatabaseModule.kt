package com.nicolas.freegames.di

import android.content.Context
import androidx.room.Room
import com.nicolas.freegames.data.local.AppDatabase
import com.nicolas.freegames.data.local.ModelGameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my-database"
        ).build()
    }

        @Provides
        @Singleton
        fun provideUserDao(appDatabase: AppDatabase): ModelGameDao {
            return appDatabase.modelGameDao()
        }
}