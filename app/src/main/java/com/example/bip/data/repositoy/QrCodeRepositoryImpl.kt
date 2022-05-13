package com.example.bip.data.repositoy

import android.util.Base64
import com.example.bip.data.db.dao.OrderDao
import com.example.bip.data.network.ApiService
import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.repository.QrCodeRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class QrCodeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : QrCodeRepository {

    override fun checkQrCode(code: String): Completable {
        return apiService.confirmQrCode(code)
    }

    override fun generateQrCode(coordinatesData: CoordinatesData, id: Int): Single<ByteArray> {
        return apiService.getQrCode(
            idOrder = id,
            latitude = coordinatesData.latitude,
            longitude = coordinatesData.longitude,
        ).map {
            Base64.decode(it.code, Base64.DEFAULT)
        }
    }
}
