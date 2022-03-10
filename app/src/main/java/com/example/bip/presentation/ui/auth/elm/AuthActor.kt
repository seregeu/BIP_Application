package com.example.bip.presentation.ui.auth.elm

import com.example.bip.domain.entity.AuthData
import com.example.bip.domain.usecase.Auth2FaUseCase
import com.example.bip.domain.usecase.AuthUseCase
import com.example.bip.domain.usecase.GetTokenUseCase
import io.reactivex.Observable
import vivid.money.elmslie.core.ActorCompat

class AuthActor(
    private val authUseCase: AuthUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val auth2FaUseCase: Auth2FaUseCase
) : ActorCompat<Command, Event> {

    override fun execute(command: Command): Observable<Event> = when (command) {
        is Command.AuthUser -> with(command) {
            authUseCase(AuthData(username = username, password = password))
                .mapEvents(
                    { token -> Event.Internal.SuccessGetNonAuthToken(token) },
                    { error -> Event.Internal.ErrorAuth(error) }
                )

        }
        Command.CheckIsAuth -> {
            getTokenUseCase()
                .mapEvents(
                    { token -> Event.Internal.SuccessGetToken(token) },
                    { error -> Event.Internal.ErrorAuth(error) }
                )
        }
        is Command.Auth2Fa -> {
            auth2FaUseCase(command.code)
                .mapEvents(
                    successEvent = Event.Internal.ErrorAuth2Fa,
                    failureEvent = Event.Internal.ErrorAuth2Fa
                )
        }
    }
}
