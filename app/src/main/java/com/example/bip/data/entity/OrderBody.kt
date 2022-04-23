package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
data class OrderBody(
    @SerialName("comment")
    val comment: String,
    @SerialName("coordinates")
    val coordinates: CoordinatesBody = CoordinatesBody(),
    @SerialName("order_cost")
    val orderCost: Int,
)
