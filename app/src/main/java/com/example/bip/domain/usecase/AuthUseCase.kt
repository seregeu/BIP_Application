package com.example.bip.domain.usecase

import com.example.bip.domain.entity.AuthData
import com.example.bip.domain.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

interface AuthUseCase : (AuthData) -> Completable {
    override fun invoke(authData: AuthData): Completable
}

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override fun invoke(authData: AuthData): Completable {
        return authRepository.authUser(authData)
    }
}
