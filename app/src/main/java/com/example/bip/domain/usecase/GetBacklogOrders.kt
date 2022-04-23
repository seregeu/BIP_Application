package com.example.bip.domain.usecase

import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface GetBacklogOrders: () -> (Single<List<OrderData>>)

class GetBacklogOrdersImpl @Inject constructor(
    private val orderRepository: OrderRepository
): GetBacklogOrders {

    override fun invoke(): Single<List<OrderData>> {
        return orderRepository.getBacklogOrders()
    }
}
