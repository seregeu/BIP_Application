package com.example.bip.domain.usecase

import com.example.bip.domain.repository.AuthRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetTokenUseCase : () -> Single<String> {
    override fun invoke(): Single<String>
}

class GetTokenUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetTokenUseCase {
    override fun invoke(): Single<String> {
        return authRepository.getTokenUseCase()
    }
}
