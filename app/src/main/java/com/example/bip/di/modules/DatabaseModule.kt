package com.example.bip.di.modules

import com.example.bip.data.db.AppDatabase
import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.db.dao.OrderDao
import com.example.bip.data.db.dao.UserDao
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
    fun provideAuthDao(database: Lazy<AppDatabase>): AuthDao = database.get().apiKeyDao()

    @Singleton
    @Provides
    fun provideUserDao(database: Lazy<AppDatabase>): UserDao = database.get().userDao()

    @Singleton
    @Provides
    fun provideOrderDao(database: Lazy<AppDatabase>): OrderDao = database.get().orderDao()
}
