package com.example.bip.domain.usecase

import com.example.bip.domain.entity.SelectOffer
import com.example.bip.domain.repository.OfferRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface SelectPhotographerUseCase : (SelectOffer, Int) -> (Completable) {
    override fun invoke(selectOffer: SelectOffer, orderId: Int): Completable
}

class SelectPhotographerUseCaseImpl @Inject constructor(
    private val offerRepository: OfferRepository
) : SelectPhotographerUseCase {

    override fun invoke(selectOffer: SelectOffer, orderId: Int): Completable {
        return offerRepository.selectOffer(selectOffer, orderId)
    }
}
