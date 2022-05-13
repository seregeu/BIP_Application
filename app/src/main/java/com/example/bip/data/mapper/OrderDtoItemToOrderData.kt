package com.example.bip.data.mapper

import com.example.bip.data.entity.OrderDtoItem
import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.entity.UserData
import com.example.bip.domain.usecase.OrderType
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class OrderDtoItemToOrderData @Inject constructor(
    private val userDtoToData: UserDtoToData
) : (OrderDtoItem, OrderType) -> (OrderData) {

    override fun invoke(orderDtoItem: OrderDtoItem, orderType: OrderType): OrderData = orderDtoItem.run {
        OrderData(
            orderCost = orderCost,
            id = id,
            userData = userDto?.let(userDtoToData) ?: UserData.Builder().build(),
            coordinatesData = CoordinatesData(),
            comment = comment,
            orderType = orderType,
        )
    }
}
