package com.example.bip.domain.repository

import com.example.bip.domain.entity.CoordinatesData
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author v.nasibullin
 */
interface QrCodeRepository {

    fun checkQrCode(code: String): Completable

    fun generateQrCode(coordinatesData: CoordinatesData): Single<String>
}
