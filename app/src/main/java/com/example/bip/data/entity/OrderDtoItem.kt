package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
class OrderDtoItem(
    @SerialName("id")
    val id: Int,
    @SerialName("photographer")
    val userDto: UserDto? = null,
    @SerialName("order_cost")
    val orderCost: Int,
    @SerialName("comment")
    val comment: String
)
