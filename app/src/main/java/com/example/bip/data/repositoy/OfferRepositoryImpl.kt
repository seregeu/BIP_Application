package com.example.bip.data.repositoy


import com.example.bip.data.db.dao.OrderDao
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
    private val userDtoToData: UserDtoToData,
    private val orderDao: OrderDao
) : OfferRepository {

    override fun getOffers(): Single<List<UserData>> {
        return orderDao.getOrderId().flatMap {
            apiService.getOffers(it)
                .map { photographersResponse ->
                    photographersResponse.photographers.map(userDtoToData)
                }
        }
    }

    override fun selectOffer(selectOffer: SelectOffer): Completable {
        return orderDao.getOrderId().flatMapCompletable {
            apiService.acceptOffer(
                idOrder = it,
                idPhotographer = selectOffer.userData.id,
                isAccept = selectOffer.isAccept
            )
        }
    }
}
