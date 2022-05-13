package com.example.bip.domain.usecase

import com.example.bip.domain.entity.UserData
import com.example.bip.domain.repository.OfferRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface GetOffersUseCase : (Int) -> (Single<List<UserData>>)

class GetOffersUseCaseImpl @Inject constructor(
    private val offerRepository: OfferRepository
) : GetOffersUseCase {
    override fun invoke(id: Int): Single<List<UserData>> {
        return offerRepository.getOffers(id)
    }
}
