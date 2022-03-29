package com.example.bip.domain.repository

import com.example.bip.domain.entity.UserData
import io.reactivex.Single

interface ProfileRepository {

    fun getProfile(): Single<UserData>
}
