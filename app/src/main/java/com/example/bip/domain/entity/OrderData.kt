package com.example.bip.domain.entity

/**
 * @author v.nasibullin
 */
class OrderData(
    val orderCost: Int,
    val id: Int,
    val userData: UserData,
    val coordinatesData: CoordinatesData = CoordinatesData(),
    val comment: String,
)
