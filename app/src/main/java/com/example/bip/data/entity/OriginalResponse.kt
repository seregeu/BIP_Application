package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
class OriginalResponse(
    @SerialName("url_original")
    val originalUrl: String
)
