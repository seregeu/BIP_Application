package com.example.bip.presentation.ui.order.photo

/**
 * @author v.nasibullin
 */
sealed class SelectOrderScreenState {
    object SuccessSelectOrder : SelectOrderScreenState()

    class ErrorSelectOrder(val error: Throwable) : SelectOrderScreenState()
}
