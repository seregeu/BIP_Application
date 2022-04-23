package com.example.bip.domain.usecase

import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface CreateOrderUseCase : (CreateOrderData) -> Completable {
    override fun invoke(createOrderData: CreateOrderData): Completable
}

class CreateOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : CreateOrderUseCase {
    override fun invoke(createOrderData: CreateOrderData): Completable {
        return orderRepository.createOrder(createOrderData)
    }
}
