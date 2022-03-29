package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentDto(
    @SerialName("avatar_url")
    val avatarUrl: String,

    @SerialName("client_id")
    val clientId: Int,

    @SerialName("content")
    val content: String,

    @SerialName("rating")
    val rating: Int,

    @SerialName("username")
    val username: String
)
