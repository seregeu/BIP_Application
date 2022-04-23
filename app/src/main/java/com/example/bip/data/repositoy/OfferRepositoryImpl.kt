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

    override fun getOffers(): Single<List<UserData>> {
        return apiService.getAllOrders()
            .flatMap { orderList ->
                apiService.getOffers(idOrder = orderList.backlog?.firstOrNull()?.id ?: -1)
            }
            .map { it.photographers.map(userDtoToData) }
    }

    override fun selectOffer(selectOffer: SelectOffer): Completable {
        return apiService.getAllOrders()
            .flatMapCompletable { orderList ->
                apiService.acceptOffer(
                    idOrder = orderList.backlog?.firstOrNull()?.id ?: -1,
                    idPhotographer = selectOffer.userData.id,
                    isAccept = selectOffer.isAccept
                )
            }
    }
}
