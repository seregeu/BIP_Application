package com.example.bip.presentation.ui.offers.client

/**
 * @author v.nasibullin
 */
sealed class OfferScreenEffect {
    object SuccessSelectOffer : OfferScreenEffect()

    class ErrorOffer(val error: Throwable) : OfferScreenEffect()
}
