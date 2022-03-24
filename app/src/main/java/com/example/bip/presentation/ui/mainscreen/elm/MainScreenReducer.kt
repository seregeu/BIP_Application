package com.example.bip.presentation.ui.mainscreen.elm

import vivid.money.elmslie.core.store.dsl_reducer.DslReducer
import javax.inject.Inject

class MainScreenReducer @Inject constructor() : DslReducer<Event, State, Effect, Command>() {

    override fun Result.reduce(event: Event) = when (event) {
        is Event.Ui.InitButtonText -> with(event) {
            commands { +Command.SelectNeedText(resources, isPhotographer) }
        }
        is Event.Internal.InitButtonText -> with(event) {
            effects { +Effect.InitButtonText(buttonTexts) }
        }
    }
}