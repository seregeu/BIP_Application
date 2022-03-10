package com.example.bip.di.modules

import com.example.bip.data.repositoy.AuthRepositoryImpl
import com.example.bip.data.repositoy.RegistrationRepositoryImpl
import com.example.bip.domain.repository.AuthRepository
import com.example.bip.domain.repository.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface RepositoryModule {

    @Reusable
    @Binds
    fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Reusable
    @Binds
    fun bindRegisterRepository(registerRepository: RegistrationRepositoryImpl): RegistrationRepository

}
