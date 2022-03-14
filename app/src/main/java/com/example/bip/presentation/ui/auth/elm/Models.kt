package com.example.bip.presentation.ui.auth.elm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class State(
    val jwtToken: @RawValue Any = Any(),
    val error: Throwable? = null,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
) : Parcelable


sealed class Event {

    sealed class Ui : Event() {
        class PressAuthButton(val username: String, val password: String) : Ui()
        object CheckDatabase : Ui()
        class PressAuth2FaButton(val code: String) : Ui()
    }

    sealed class Internal : Event() {
        object SuccessGetToken : Internal()
        class SuccessGetNonAuthToken(val token: String) : Internal()
        class ErrorAuth(val error: Throwable) : Internal()
        class ErrorAuth2Fa(val error: Throwable) : Internal()
    }
}

sealed class Effect {
    object SuccessAuth : Effect()
    class ErrorAuth(val error: Throwable) : Effect()
}

sealed class Command {
    class AuthUser(val username: String, val password: String) : Command()
    class Auth2Fa(val code: String) : Command()
    object CheckIsAuth : Command()
}
