package com.example.bip.data.mapper

import com.example.bip.data.entity.OrderBody
import com.example.bip.domain.entity.CreateOrderData
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class OrderDataToBody @Inject constructor() : (CreateOrderData) -> (OrderBody) {
    override fun invoke(createOrderData: CreateOrderData): OrderBody = createOrderData.run {
        OrderBody(
            comment = comment,
            orderCost = orderCost,
        )
    }
}
