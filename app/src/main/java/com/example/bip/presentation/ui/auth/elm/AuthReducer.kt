package com.example.bip.presentation.ui.auth.elm

import vivid.money.elmslie.core.store.dsl_reducer.DslReducer
import javax.inject.Inject

class AuthReducer @Inject constructor() : DslReducer<Event, State, Effect, Command>() {

    override fun Result.reduce(event: Event) = when (event) {
        is Event.Internal.ErrorAuth -> {
            effects { +Effect.ErrorAuth(event.error) }
        }
        is Event.Internal.SuccessGetNonAuthToken -> {
            state { copy(jwtToken = event.token, isSuccess = true) }
        }
        is Event.Internal.SuccessGetToken -> {
            successAuth(event.token)
        }
        is Event.Internal.ErrorAuth2Fa -> {
            effects { +Effect.ErrorAuth(IllegalArgumentException("auth2fa error token")) }
        }
        Event.Ui.CheckDatabase -> {
            commands { +Command.CheckIsAuth }
        }
        is Event.Ui.PressAuthButton -> with(event) {
            commands { +Command.AuthUser(username, password) }
        }
        is Event.Ui.PressAuth2FaButton -> with(event) {
            commands { +Command.Auth2Fa(event.code) }
        }
    }

    private fun Result.successAuth(apiToken: String) {
        if (apiToken.isNotEmpty()) {
            effects { +Effect.SuccessAuth }
        } else {
            effects { +Effect.ErrorAuth(IllegalArgumentException("required token")) }
        }
    }
}
