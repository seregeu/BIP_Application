package com.example.bip.presentation.ui.mainscreen.elm

import android.content.res.Resources
import android.os.Parcelable
import com.example.bip.presentation.ui.mainscreen.entity.ConfigUI
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    val configUI: ConfigUI,
    val error: Throwable? = null,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
) : Parcelable


sealed class Event {

    sealed class Ui : Event() {
        class InitButtonText(val resources: Resources, val isPhotographer: Boolean) : Ui()
        object Exit : Ui()
    }

    sealed class Internal : Event() {
        class InitButtonText(val buttonTexts: List<String>) : Internal()
        object Exit : Internal()
    }
}

sealed class Effect {
    class InitButtonText(val buttonTexts: List<String>) : Effect()
    object Exit : Effect()
}

sealed class Command {
    class SelectNeedText(val resources: Resources, val isPhotographer: Boolean) : Command()
    object Exit : Command()
}
