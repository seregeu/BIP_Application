package com.example.bip.di.modules

import com.example.bip.di.MainPhotographerScope
import com.example.bip.presentation.ui.mainscreen.GetNeedConfigurations
import com.example.bip.presentation.ui.mainscreen.elm.*
import dagger.Module
import dagger.Provides
import vivid.money.elmslie.core.store.Store

@Module
class MainPhotographScreenModule {

    @MainPhotographerScope
    @Provides
    fun provideMainPhotographScreenActor(
        getNeedConfigurations: GetNeedConfigurations
    ): MainScreenActor {
        return MainScreenActor(getNeedConfigurations)
    }

    @MainPhotographerScope
    @Provides
    fun provideMainPhotographScreenScope(
        mainScreenActor: MainScreenActor,
        mainScreenReducer: MainScreenReducer
    ): Store<Event, Effect, State> {
        return MainScreenFactory(mainScreenActor, mainScreenReducer).provide()
    }
}
