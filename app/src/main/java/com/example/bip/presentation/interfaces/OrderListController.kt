package com.example.bip.presentation.interfaces

import com.example.bip.domain.entity.OrderData

/**
 * @author v.nasibullin
 */
interface OrderListController {

    fun orderSelect(orderData: OrderData)
}
