package com.example.bip.domain.repository

import com.example.bip.domain.entity.AuthData
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {

    fun authUser(authData: AuthData): Single<String>

    fun secondAuth(code: String): Completable

    fun getTokenUseCase(): Single<String>
}
