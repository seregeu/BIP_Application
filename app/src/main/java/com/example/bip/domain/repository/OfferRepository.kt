package com.example.bip.domain.repository

import com.example.bip.domain.entity.SelectOffer
import com.example.bip.domain.entity.UserData
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author v.nasibullin
 */
interface OfferRepository {

    fun getOffers(id :Int): Single<List<UserData>>

    fun selectOffer(selectOffer: SelectOffer, orderId: Int): Completable
}
