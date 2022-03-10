package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthBody(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
