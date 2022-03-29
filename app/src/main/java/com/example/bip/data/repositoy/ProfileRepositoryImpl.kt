package com.example.bip.data.repositoy

import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.mapper.UserDtoToData
import com.example.bip.data.network.ApiService
import com.example.bip.domain.entity.UserData
import com.example.bip.domain.repository.ProfileRepository
import io.reactivex.Single
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val authDao: AuthDao,
    private val userDtoToData: UserDtoToData
) : ProfileRepository {

    override fun getProfile(): Single<UserData> {
        return authDao.getAuth()
            .flatMap {
                apiService.getUserProfile(it.id.toString())
            }.map(userDtoToData)
    }
}
