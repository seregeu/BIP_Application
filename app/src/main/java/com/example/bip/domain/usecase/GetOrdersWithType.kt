package com.example.bip.domain.usecase

import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface GetOrdersWithType : (OrderType) -> (Single<List<OrderData>>)

class GetOrdersWithTypeImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : GetOrdersWithType {

    override fun invoke(orderType: OrderType): Single<List<OrderData>> {
        return orderRepository.getAllClientOrders()
            .map { it.filter { order -> order.orderType == orderType } }
    }
}

enum class OrderType {
    BACKLOG,
    ACTIVE,
    FINISHED,
}
