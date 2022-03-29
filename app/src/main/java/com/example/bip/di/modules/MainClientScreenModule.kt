package com.example.bip.di.modules

import com.example.bip.di.MainClientScreenScope
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
        getNeedConfigurations: GetNeedConfigurations
    ): MainScreenActor {
        return MainScreenActor(getNeedConfigurations)
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
