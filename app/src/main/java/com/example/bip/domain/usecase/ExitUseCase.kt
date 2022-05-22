package com.example.bip.domain.usecase

import com.example.bip.domain.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface ExitUseCase: () -> Completable

class ExitUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
): ExitUseCase{
    override fun invoke(): Completable {
        return authRepository.exit()
    }

}
