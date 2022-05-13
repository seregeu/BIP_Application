package com.example.bip.presentation.ui.orderlist

sealed class SelectOrderScreenState {
    object SuccessSelectOrder : SelectOrderScreenState()

    class ErrorSelectOrder(val error: Throwable) : SelectOrderScreenState()
}
