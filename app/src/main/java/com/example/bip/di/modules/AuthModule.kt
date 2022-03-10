package com.example.bip.di.modules

import com.example.bip.di.AuthScope
import com.example.bip.domain.usecase.Auth2FaUseCase
import com.example.bip.domain.usecase.AuthUseCase
import com.example.bip.domain.usecase.GetTokenUseCase
import com.example.bip.presentation.ui.auth.elm.*
import dagger.Module
import dagger.Provides
import vivid.money.elmslie.core.store.Store

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthActor(
        authUseCase: AuthUseCase,
        getTokenUseCase: GetTokenUseCase,
        auth2FaUseCase: Auth2FaUseCase
    ): AuthActor {
        return AuthActor(authUseCase, getTokenUseCase, auth2FaUseCase)
    }

    @AuthScope
    @Provides
    fun provideAuthStore(
        authActor: AuthActor,
        authReducer: AuthReducer
    ): Store<Event, Effect, State> {
        return AuthStoreFactory(authActor, authReducer).provide()
    }
}
