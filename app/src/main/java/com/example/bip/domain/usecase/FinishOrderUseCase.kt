package com.example.bip.domain.usecase

import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface FinishOrderUseCase : (Int) -> (Single<String>)

class FinishOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : FinishOrderUseCase {
    override fun invoke(orderId: Int): Single<String> {
        return orderRepository.finishOrder(orderId)
    }
}
