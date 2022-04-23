package com.example.bip.domain.usecase

import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface SelectOrderUseCase : (OrderData) -> (Completable)

class SelectOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : SelectOrderUseCase {
    override fun invoke(orderData: OrderData): Completable {
        return orderRepository.selectOrder(orderData)
    }
}
