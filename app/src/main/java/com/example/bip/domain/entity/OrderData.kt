package com.example.bip.domain.entity

import com.example.bip.domain.usecase.OrderType

/**
 * @author v.nasibullin
 */
class OrderData(
    val orderCost: Int,
    val id: Int,
    val userData: UserData = UserData.Builder().build(),
    val coordinatesData: CoordinatesData = CoordinatesData(),
    val comment: String,
    val orderType: OrderType = OrderType.BACKLOG,
)
