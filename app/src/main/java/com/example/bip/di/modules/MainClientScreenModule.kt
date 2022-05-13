package com.example.bip.di.modules

import com.example.bip.di.MainClientScreenScope
import com.example.bip.domain.usecase.ExitUseCase
import com.example.bip.presentation.ui.mainscreen.GetNeedConfigurations
import com.example.bip.presentation.ui.mainscreen.elm.*
import dagger.Module
import dagger.Provides
import vivid.money.elmslie.core.store.Store

@Module
class MainClientScreenModule {

    @MainClientScreenScope
    @Provides
    fun provideMainClientScreenActor(
        getNeedConfigurations: GetNeedConfigurations,
        exitUseCase: ExitUseCase
    ): MainScreenActor {
        return MainScreenActor(getNeedConfigurations, exitUseCase)
    }

    @MainClientScreenScope
    @Provides
    fun provideMainClientScreenScope(
        mainScreenActor: MainScreenActor,
        mainScreenReducer: MainScreenReducer
    ): Store<Event, Effect, State> {
        return MainScreenFactory(mainScreenActor, mainScreenReducer).provide()
    }
}
