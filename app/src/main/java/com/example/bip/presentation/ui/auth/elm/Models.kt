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
        class PressButton(val username: String, val password: String) : Ui()
        object CheckDatabase : Ui()
    }

    sealed class Internal : Event() {
        class SuccessGetToken(val token: String) : Internal()
        object SuccessAuth : Internal()
        object ErrorAuth : Internal()
    }
}

sealed class Effect {
    object SuccessAuth : Effect()
    object ErrorAuth : Effect()
}

sealed class Command {
    class AuthUser(val username: String, val password: String) : Command()
    object CheckIsAuth : Command()
}
