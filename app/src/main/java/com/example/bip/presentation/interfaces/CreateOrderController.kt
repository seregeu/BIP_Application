package com.example.bip.presentation.interfaces

import com.example.bip.domain.entity.CreateOrderData

/**
 * @author v.nasibullin
 */
interface CreateOrderController {

    fun createOrder(createOrderData: CreateOrderData)
}
