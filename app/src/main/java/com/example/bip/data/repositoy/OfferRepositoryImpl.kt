package com.example.bip.data.repositoy


import com.example.bip.data.mapper.UserDtoToData
import com.example.bip.data.network.ApiService
import com.example.bip.domain.entity.SelectOffer
import com.example.bip.domain.entity.UserData
import com.example.bip.domain.repository.OfferRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class OfferRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDtoToData: UserDtoToData
) : OfferRepository {

    override fun getOffers(id: Int): Single<List<UserData>> {
        return apiService.getOffers(idOrder = id)
            .map { it.photographers.map(userDtoToData) }
    }

    override fun selectOffer(selectOffer: SelectOffer, orderId: Int): Completable {
        return apiService.acceptOffer(
            idOrder = orderId,
            idPhotographer = selectOffer.userData.id,
            isAccept = selectOffer.isAccept
        )
    }
}
