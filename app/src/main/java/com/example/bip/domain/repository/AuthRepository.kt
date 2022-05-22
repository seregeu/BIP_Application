package com.example.bip.domain.repository

import com.example.bip.domain.entity.AuthData
import io.reactivex.Completable

interface AuthRepository {

    fun authUser(authData: AuthData): Completable

    fun secondAuth(code: String): Completable

    fun exit(): Completable
}
