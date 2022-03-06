package com.example.bip.presentation.ui.auth.elm

import com.example.bip.domain.entity.AuthData
import com.example.bip.domain.usecase.AuthUseCase
import com.example.bip.domain.usecase.GetTokenUseCase
import io.reactivex.Observable
import vivid.money.elmslie.core.ActorCompat

class AuthActor(
    private val authUseCase: AuthUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ActorCompat<Command, Event> {

    override fun execute(command: Command): Observable<Event> = when (command) {
        is Command.AuthUser -> with(command) {
            authUseCase(AuthData(username = username, password = password))
                .mapEvents(
                    successEvent = Event.Internal.SuccessAuth,
                    failureEvent = Event.Internal.ErrorAuth
                )

        }
        Command.CheckIsAuth -> {
            getTokenUseCase()
                .mapEvents(
                    { token -> Event.Internal.SuccessGetToken(token) },
                    { _ -> Event.Internal.ErrorAuth }
                )
        }
    }
}
