package com.example.bip.presentation.ui.register.elm

import com.example.bip.domain.usecase.RegisterUseCase
import io.reactivex.Observable
import vivid.money.elmslie.core.ActorCompat

class RegisterActor(
    private val registerUseCase: RegisterUseCase
) : ActorCompat<Command, Event> {
    override fun execute(command: Command): Observable<Event> = when (command) {
        is Command.RegisterUser -> {
            registerUseCase(command.userData)
                .mapEvents(
                    Event.Internal.SuccessRegister,
                    Event.Internal.ErrorRegister
                )
        }
    }
}
