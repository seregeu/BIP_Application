package com.example.bip.domain.usecase

import com.example.bip.domain.entity.AuthData
import com.example.bip.domain.repository.AuthRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface AuthUseCase : (AuthData) -> Single<String> {
    override fun invoke(authData: AuthData): Single<String>
}

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override fun invoke(authData: AuthData): Single<String> {
        return authRepository.authUser(authData)
    }
}
