package com.example.bip.domain.usecase

import com.example.bip.domain.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

interface Auth2FaUseCase : (String) -> Completable {
    override fun invoke(code: String): Completable
}

class Auth2FaUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : Auth2FaUseCase {

    override fun invoke(code: String): Completable {
        return authRepository.secondAuth(code)
    }
}
