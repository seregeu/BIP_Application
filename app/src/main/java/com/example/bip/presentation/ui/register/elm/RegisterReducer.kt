package com.example.bip.presentation.ui.register.elm

import vivid.money.elmslie.core.store.dsl_reducer.DslReducer
import javax.inject.Inject

class RegisterReducer @Inject constructor() : DslReducer<Event, State, Effect, Command>() {
    override fun Result.reduce(event: Event) = when (event) {
        Event.Internal.ErrorRegister -> {
            effects { +Effect.ErrorRegister }
        }
        Event.Internal.SuccessRegister -> {
            effects { +Effect.SuccessRegister }
        }
        is Event.Ui.RegisterUser -> {
            commands { +Command.RegisterUser(userData = event.userData) }
        }
    }
}
