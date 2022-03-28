package com.example.bip.domain.usecase

import com.example.bip.domain.entity.UserData
import com.example.bip.domain.repository.ProfileRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetProfileUseCase : () -> (Single<UserData>) {
    override fun invoke(): Single<UserData>
}

class GetProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
) : GetProfileUseCase {
    override fun invoke(): Single<UserData> {
        return profileRepository.getProfile()
    }
}
