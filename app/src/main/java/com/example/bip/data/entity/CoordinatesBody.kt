package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
data class CoordinatesBody(
    @SerialName("latitude")
    val latitude: Float = 0F,
    @SerialName("longitude")
    val longitude: Float = 0F,
)
