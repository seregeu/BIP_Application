package com.example.bip.presentation.ui.register.elm

import android.os.Parcelable
import com.example.bip.domain.entity.UserData
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class State(
    val item: @RawValue Any = Any(),
    val error: Throwable? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isUpdate: Boolean = false
) : Parcelable

sealed class Event {

    sealed class Ui : Event() {
        class RegisterUser(val userData: UserData) : Ui()
    }

    sealed class Internal : Event() {
        object SuccessRegister : Internal()
        object ErrorRegister : Internal()
    }
}

sealed class Effect {
    object SuccessRegister : Effect()
    object ErrorRegister : Effect()
}

sealed class Command {
    class RegisterUser(val userData: UserData) : Command()
}
