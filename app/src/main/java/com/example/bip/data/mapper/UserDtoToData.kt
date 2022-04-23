package com.example.bip.data.mapper

import com.example.bip.data.entity.UserDto
import com.example.bip.domain.entity.UserData
import javax.inject.Inject

class UserDtoToData @Inject constructor() : (UserDto) -> (UserData) {

    override fun invoke(userDto: UserDto): UserData = userDto.run {
        UserData(
            id = id,
            firstName = firstName,
            secondName = secondName,
            isPhotographer = isPhotographer,
            avatarUrl = avatarUrl,
            phoneNumber = phoneNumber,
            mail = mail ?: "",
            username = username,
            password = password ?: ""
        )
    }
}
