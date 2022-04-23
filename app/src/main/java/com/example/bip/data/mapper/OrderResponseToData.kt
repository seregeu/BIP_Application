package com.example.bip.data.mapper

import com.example.bip.data.entity.OrderResponse
import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.entity.OrderData
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class OrderResponseToData @Inject constructor(
    private val userDtoToData: UserDtoToData
) : (OrderResponse) -> (OrderData) {
    override fun invoke(orderResponse: OrderResponse): OrderData = orderResponse.run {
        OrderData(
            id = id,
            orderCost = orderCost,
            userData = userDtoToData(user),
            coordinatesData = CoordinatesData(),
            comment = comment,
        )
    }
}
