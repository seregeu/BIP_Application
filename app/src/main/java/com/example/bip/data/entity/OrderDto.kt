package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
data class OrderDto(
    @SerialName("client_current_location")
    val clientCurrentLocation: CoordinatesBody,

    @SerialName("client_id")
    val clientId: Int,

    @SerialName("comment")
    val comment: String,

    @SerialName("coordinates")
    val coordinatesBody: CoordinatesBody,

    @SerialName("id")
    val id: Int,

    @SerialName("order_cost")
    val orderCost: Int,

    @SerialName("order_state")
    val orderState: String,

    @SerialName("photographer_id")
    val photographerId: Int
)
