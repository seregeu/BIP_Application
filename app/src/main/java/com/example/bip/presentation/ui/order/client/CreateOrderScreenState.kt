package com.example.bip.presentation.ui.order.client

/**
 * @author v.nasibullin
 */
sealed class CreateOrderScreenState {
    object SuccessCreateOrder : CreateOrderScreenState()

    class ErrorCreateOrder(val error: Throwable) : CreateOrderScreenState()
}
