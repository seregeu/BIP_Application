package com.example.bip.presentation.ui.register.elm

import vivid.money.elmslie.core.ElmStoreCompat

class RegisterStoreFactory(
    private val registerActor: RegisterActor,
    private val registerReducer: RegisterReducer
) {
    private val store by lazy {
        ElmStoreCompat(
            initialState = State(),
            reducer = registerReducer,
            actor = registerActor
        )
    }

    fun provide() = store
}
