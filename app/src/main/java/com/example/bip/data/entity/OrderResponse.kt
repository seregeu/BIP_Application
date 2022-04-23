package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
data class OrderResponse(
    @SerialName("client")
    val user: UserDto,
    @SerialName("id")
    val id: Int,
    @SerialName("order_cost")
    val orderCost: Int,
    @SerialName("comment")
    val comment: String,
)

@Serializable
data class OrderListResponse(
    @SerialName("order_data")
    val orderResponse: List<OrderResponse>,
)
