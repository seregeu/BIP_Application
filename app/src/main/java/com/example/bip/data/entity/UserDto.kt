package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserDto(
    @SerialName("id")
    val id: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("second_name")
    val secondName: String,
    @SerialName("is_photographer")
    val isPhotographer: Boolean,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("mail")
    val mail: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
