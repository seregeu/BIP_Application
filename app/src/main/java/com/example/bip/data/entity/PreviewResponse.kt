package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
class PreviewResponse(
    @SerialName("url_watermark")
    val urlWatermark: String
)
