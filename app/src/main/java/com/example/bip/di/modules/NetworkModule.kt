package com.example.bip.di.modules

import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideApiService(authDao: AuthDao): ApiService {
        return ApiService.create(authDao)
    }
}
