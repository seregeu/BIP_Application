package com.example.bip.data.repositoy

import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.entity.AuthBody
import com.example.bip.data.entity.AuthEntity
import com.example.bip.data.entity.AuthSecondEntity
import com.example.bip.data.mapper.UserDtoToData
import com.example.bip.data.network.ApiService
import com.example.bip.domain.entity.AuthData
import com.example.bip.domain.repository.AuthRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val authDao: AuthDao
) : AuthRepository {

    @Inject
    lateinit var userDtoToData: UserDtoToData

    override fun authUser(authData: AuthData): Single<String> {
        return authData.run {
            apiService.authUser(AuthBody(username = username, password = password))
                .map { it.jwt }
        }
    }

    override fun secondAuth(code: String): Completable {
        return apiService.auth2Fa(AuthSecondEntity(code)).flatMapCompletable {
            authDao.insertAuthData(AuthEntity(id = it.user.id, token = it.jwt))
        }
    }

    override fun getTokenUseCase(): Single<String> = run {
        Single.fromCallable {
            authDao.getToken()
        }
    }
}
