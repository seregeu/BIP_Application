package com.example.bip.data.repositoy

import com.example.bip.data.mapper.UserDataToEntity
import com.example.bip.data.network.ApiService
import com.example.bip.domain.entity.UserData
import com.example.bip.domain.repository.RegistrationRepository
import io.reactivex.Completable
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDataToEntity: UserDataToEntity
) : RegistrationRepository {

    override fun registerUser(userData: UserData): Completable {
        val userEntity = userDataToEntity(userData)
        return apiService.registerUser(userEntity)
    }
}
