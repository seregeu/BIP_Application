package com.example.bip.data.mapper

import com.example.bip.data.entity.AllOrdersResponse
import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.usecase.OrderType
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class AllOrderResponseToData @Inject constructor(
    private val orderDtoItemToOrderData: OrderDtoItemToOrderData
) : (AllOrdersResponse) -> (List<OrderData>) {

    override fun invoke(allOrdersResponse: AllOrdersResponse): List<OrderData> {
        val orderList = mutableListOf<OrderData>()
        orderList.addAll(allOrdersResponse.backlog?.map { orderDtoItemToOrderData(it, OrderType.BACKLOG) } ?: emptyList())
        orderList.addAll(allOrdersResponse.active?.map { orderDtoItemToOrderData(it, OrderType.ACTIVE) } ?: emptyList())
        orderList.addAll(allOrdersResponse.finished?.map { orderDtoItemToOrderData(it, OrderType.FINISHED) } ?: emptyList())
        return orderList.toList()
    }
}
