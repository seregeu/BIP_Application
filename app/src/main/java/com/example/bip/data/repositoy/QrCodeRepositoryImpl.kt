package com.example.bip.data.repositoy

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
    private val orderDao: OrderDao,
) : QrCodeRepository {

    override fun checkQrCode(code: String): Completable {
        return apiService.confirmQrCode(code)
    }

    override fun generateQrCode(coordinatesData: CoordinatesData): Single<String> {
        return apiService.getAllOrders()
            .flatMap {
                apiService.getQrCode(
                    idOrder = it.active?.firstOrNull()?.id ?: -1,
                    latitude = coordinatesData.latitude,
                    longitude = coordinatesData.longitude,
                )
            }
            .map { it.code }
    }
}
