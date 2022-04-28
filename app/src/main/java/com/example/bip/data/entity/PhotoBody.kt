package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
class PhotoBody(
    @SerialName("url_origin")
    val urlOrigin: String,
    @SerialName("url_watermark")
    val waterMark: String,
)
