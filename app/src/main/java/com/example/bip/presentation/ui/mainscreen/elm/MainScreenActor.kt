package com.example.bip.presentation.ui.mainscreen.elm

import com.example.bip.presentation.ui.mainscreen.GetNeedConfigurations
import io.reactivex.Observable
import vivid.money.elmslie.core.ActorCompat

class MainScreenActor(
    private val getNeedConfigurations: GetNeedConfigurations
) : ActorCompat<Command, Event> {
    override fun execute(command: Command): Observable<Event> = when (command) {
        is Command.SelectNeedText -> {
            getNeedConfigurations(command.resources, command.isPhotographer)
                .mapEvents(
                    { result -> Event.Internal.InitButtonText(result) },
                    { _ -> Event.Internal.InitButtonText(listOf("", "", "")) }
                )
        }
    }
}