package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
data class QrCodeResponse(
    @SerialName("code")
    val code: String
)
