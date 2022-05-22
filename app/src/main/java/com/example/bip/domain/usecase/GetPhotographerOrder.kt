package com.example.bip.domain.usecase

import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface GetPhotographerOrder: () -> (Single<List<OrderData>>)

class GetPhotographerOrderImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : GetPhotographerOrder {

    override fun invoke(): Single<List<OrderData>> {
        return orderRepository.getAllPhotographersOrders()
            .map { it.filter { orderData -> orderData.orderType == OrderType.ACTIVE } }
    }
}
