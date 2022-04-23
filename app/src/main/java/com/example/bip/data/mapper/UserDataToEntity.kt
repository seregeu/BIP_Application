package com.example.bip.data.mapper

import com.example.bip.data.entity.UserBody
import com.example.bip.domain.entity.UserData
import javax.inject.Inject

class UserDataToEntity @Inject constructor() : (UserData) -> UserBody {
    override fun invoke(userData: UserData): UserBody = userData.run {
        UserBody(
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
