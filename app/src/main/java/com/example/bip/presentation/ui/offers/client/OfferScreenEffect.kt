package com.example.bip.presentation.ui.offers.client

/**
 * @author v.nasibullin
 */
sealed class OfferScreenEffect {
    class SuccessSelectOffer(val isAccept: Boolean) : OfferScreenEffect()

    class ErrorOffer(val error: Throwable) : OfferScreenEffect()
}
