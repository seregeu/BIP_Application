package com.example.bip.presentation.ui.auth.elm

import vivid.money.elmslie.core.store.dsl_reducer.DslReducer
import javax.inject.Inject

class AuthReducer @Inject constructor() : DslReducer<Event, State, Effect, Command>() {

    override fun Result.reduce(event: Event) = when (event) {
        Event.Internal.ErrorAuth -> {
            effects { +Effect.ErrorAuth }
        }
        Event.Internal.SuccessAuth -> {
            effects { +Effect.SuccessAuth }
        }
        is Event.Internal.SuccessGetToken -> {
            successAuth(event.token)
        }
        Event.Ui.CheckDatabase -> {
            commands { +Command.CheckIsAuth }
        }
        is Event.Ui.PressButton -> with(event) {
            commands { +Command.AuthUser(event.username, password) }
        }
    }

    private fun Result.successAuth(apiToken: String) {
        if (apiToken.isNotEmpty()) {
            effects { +Effect.SuccessAuth }
        } else {
            effects { +Effect.ErrorAuth }
        }
    }
}
