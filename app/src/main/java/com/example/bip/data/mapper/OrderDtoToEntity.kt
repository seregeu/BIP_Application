package com.example.bip.data.mapper

import com.example.bip.data.entity.OrderDto
import com.example.bip.data.entity.OrderEntity
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class OrderDtoToEntity @Inject constructor() : (OrderDto) -> (OrderEntity) {
    override fun invoke(orderDto: OrderDto): OrderEntity = orderDto.run {
        OrderEntity(
            clientId = clientId,
            comment = comment,
            id = id,
            orderCost = orderCost,
            orderState = orderState,
            photographerId = photographerId
        )
    }
}
