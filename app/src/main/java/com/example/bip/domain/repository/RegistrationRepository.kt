package com.example.bip.domain.repository

import com.example.bip.domain.entity.UserData
import io.reactivex.Completable

interface RegistrationRepository {

    fun registerUser(userData: UserData): Completable
}