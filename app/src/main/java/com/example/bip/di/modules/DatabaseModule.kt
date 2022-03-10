package com.example.bip.di.modules

import com.example.bip.data.db.AppDatabase
import com.example.bip.data.db.dao.AuthDao
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase = AppDatabase.instance

    @Singleton
    @Provides
    fun provideMessageDao(database: Lazy<AppDatabase>): AuthDao = database.get().apiKeyDao()
}
