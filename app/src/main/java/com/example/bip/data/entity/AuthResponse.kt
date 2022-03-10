package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("jwt")
    val jwt: String,
    @SerialName("user")
    val user: UserDto
)

@Serializable
data class AuthResponseFirst(
    @SerialName("jwt")
    val jwt: String
)
