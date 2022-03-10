package com.example.bip.domain.usecase

import com.example.bip.domain.entity.UserData
import com.example.bip.domain.repository.RegistrationRepository
import io.reactivex.Completable
import javax.inject.Inject

interface RegisterUseCase : (UserData) -> Completable {
    override fun invoke(userData: UserData): Completable
}

class RegisterUseCaseImpl @Inject constructor(
    private val repository: RegistrationRepository
) : RegisterUseCase {

    override fun invoke(userData: UserData): Completable {
        return repository.registerUser(userData)
    }
}
