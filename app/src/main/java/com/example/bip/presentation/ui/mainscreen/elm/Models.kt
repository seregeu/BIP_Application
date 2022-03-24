package com.example.bip.presentation.ui.mainscreen.elm

import android.content.res.Resources
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
        class InitButtonText(val resources: Resources, val isPhotographer: Boolean) : Ui()
    }

    sealed class Internal : Event() {
        class InitButtonText(val buttonTexts: List<String>) : Internal()
    }
}

sealed class Effect {
    class InitButtonText(val buttonTexts: List<String>) : Effect()
}

sealed class Command {
    class SelectNeedText(val resources: Resources, val isPhotographer: Boolean) : Command()
}
