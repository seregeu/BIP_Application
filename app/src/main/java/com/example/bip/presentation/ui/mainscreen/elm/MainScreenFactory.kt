package com.example.bip.presentation.ui.mainscreen.elm

import vivid.money.elmslie.core.ElmStoreCompat

class MainScreenFactory(
    private val mainScreenActor: MainScreenActor,
    private val mainScreenReducer: MainScreenReducer
) {
    private val store by lazy {
        ElmStoreCompat(
            initialState = State(),
            reducer = mainScreenReducer,
            actor = mainScreenActor
        )
    }

    fun provide() = store

}