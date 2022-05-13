package com.example.bip.presentation.ui.mainscreen.elm

import com.example.bip.presentation.ui.mainscreen.entity.ConfigUI
import vivid.money.elmslie.core.store.dsl_reducer.DslReducer
import javax.inject.Inject

class MainScreenReducer @Inject constructor() : DslReducer<Event, State, Effect, Command>() {

    override fun Result.reduce(event: Event) = when (event) {
        is Event.Ui.InitButtonText -> with(event) {
            commands { +Command.SelectNeedText(resources, isPhotographer) }
        }
        is Event.Internal.InitButtonText -> with(event) {
            state {
                copy(
                    configUI = ConfigUI(
                        mainButton = buttonTexts[0],
                        qrCodeAction = buttonTexts[1],
                        anotherButton = buttonTexts[2],
                        money = buttonTexts[3]
                    ),
                    isSuccess = true
                )
            }
        }
    }
}
