package com.example.bip.domain.repository

import com.example.bip.domain.entity.SelectOffer
import com.example.bip.domain.entity.UserData
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author v.nasibullin
 */
interface OfferRepository {

    fun getOffers(): Single<List<UserData>>

    fun selectOffer(selectOffer: SelectOffer): Completable
}
