package com.example.bip.di.modules

import com.example.bip.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface UseCaseModule {

    @Reusable
    @Binds
    fun bindRegistration(registerUseCase: RegisterUseCaseImpl): RegisterUseCase

    @Reusable
    @Binds
    fun bindAuthorization(authUseCase: AuthUseCaseImpl): AuthUseCase

    @Reusable
    @Binds
    fun bindGetToken(getTokenUseCase: GetTokenUseCaseImpl): GetTokenUseCase
}
