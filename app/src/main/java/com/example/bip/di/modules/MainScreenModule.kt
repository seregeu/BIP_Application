package com.example.bip.di.modules

import com.example.bip.di.MainScreenScope
import com.example.bip.presentation.ui.mainscreen.GetNeedConfigurations
import com.example.bip.presentation.ui.mainscreen.elm.*
import dagger.Module
import dagger.Provides
import vivid.money.elmslie.core.store.Store

@Module
class MainScreenModule {

    @MainScreenScope
    @Provides
    fun provideMainScreenActor(
        getNeedConfigurations: GetNeedConfigurations
    ): MainScreenActor {
        return MainScreenActor(getNeedConfigurations)
    }

    @MainScreenScope
    @Provides
    fun provideMainScreenScope(
        mainScreenActor: MainScreenActor,
        mainScreenReducer: MainScreenReducer
    ): Store<Event, Effect, State> {
        return MainScreenFactory(mainScreenActor, mainScreenReducer).provide()
    }
}