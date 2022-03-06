package com.example.bip.data.mapper

import com.example.bip.data.entity.UserEntity
import com.example.bip.domain.entity.UserData
import javax.inject.Inject

class UserDataToEntity @Inject constructor() : (UserData) -> UserEntity {
    override fun invoke(userData: UserData): UserEntity = userData.run {
        UserEntity(
            firstName = firstName,
            secondName = secondName,
            isPhotographer = isPhotographer,
            avatarUrl = avatarUrl,
            phoneNumber = phoneNumber,
            mail = mail,
            username = username,
            password = password
        )
    }
}