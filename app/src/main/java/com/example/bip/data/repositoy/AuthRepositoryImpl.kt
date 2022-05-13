package com.example.bip.data.repositoy

import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.db.dao.UserDao
import com.example.bip.data.entity.AuthBody
import com.example.bip.data.entity.AuthEntity
import com.example.bip.data.entity.AuthSecondEntity
import com.example.bip.data.entity.UserDbEntity
import com.example.bip.data.mapper.UserDtoToData
import com.example.bip.data.network.ApiService
import com.example.bip.domain.entity.AuthData
import com.example.bip.domain.repository.AuthRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val authDao: AuthDao,
    private val userDao: UserDao
) : AuthRepository {

    @Inject
    lateinit var userDtoToData: UserDtoToData

    override fun authUser(authData: AuthData): Completable {
        return authData.run {
            apiService.authUser(AuthBody(username = username, password = password))
                .flatMap { authDao.insertAndDelete(AuthEntity(id = 0, token = it.jwt, userId = 0)) }
                .ignoreElement()
        }
    }


    override fun secondAuth(code: String): Completable {
        return apiService.auth2Fa(AuthSecondEntity(code)).flatMapCompletable { authResponse ->
            authDao.insertAndDelete(
                AuthEntity(
                    id = authResponse.user.id,
                    token = authResponse.jwt,
                    userId = authResponse.user.id
                )
            ).flatMapCompletable {
                userDao.insert(
                    UserDbEntity(
                        id = 0,
                        firstName = authResponse.user.firstName,
                        secondName = authResponse.user.secondName,
                        isPhotographer = authResponse.user.isPhotographer,
                        avatarUrl = authResponse.user.avatarUrl,
                        phoneNumber = authResponse.user.phoneNumber,
                        mail = authResponse.user.mail ?: "",
                        username = authResponse.user.username,
                        password = authResponse.user.password ?: ""
                    )
                )
            }
        }
    }

    override fun exit(): Completable {
        return Observable.fromCallable { authDao.delete() }
            .flatMapCompletable { if (it != 1) Completable.complete() else Completable.error(IllegalAccessError()) }
    }
}
