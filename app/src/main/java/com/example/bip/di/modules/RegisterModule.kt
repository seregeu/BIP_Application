package com.example.bip.di.modules

import com.example.bip.di.RegisterScope
import com.example.bip.domain.usecase.RegisterUseCase
import com.example.bip.presentation.ui.register.elm.*
import dagger.Module
import dagger.Provides
import vivid.money.elmslie.core.store.Store

@Module
class RegisterModule {

    @RegisterScope
    @Provides
    fun provideRegisterActor(
        registerUseCase: RegisterUseCase
    ): RegisterActor {
        return RegisterActor(registerUseCase)
    }

    @RegisterScope
    @Provides
    fun provideRegisterStore(
        registerActor: RegisterActor,
        registerReducer: RegisterReducer
    ): Store<Event, Effect, State> {
        return RegisterStoreFactory(
            registerActor,
            registerReducer
        ).provide()
    }
}
