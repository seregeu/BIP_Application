package com.example.bip.domain.usecase

import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.repository.QrCodeRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface ConfirmQrCodeUseCase : (String, CoordinatesData) -> (Completable) {
    override fun invoke(qrcode: String, coordinatesData: CoordinatesData): Completable
}

class ConfirmQrCodeUseCaseImpl @Inject constructor(
    private val qrCodeRepository: QrCodeRepository,
) : ConfirmQrCodeUseCase {

    override fun invoke(qrcode: String, coordinatesData: CoordinatesData): Completable {
        return qrCodeRepository.checkQrCode(qrcode, coordinatesData)
    }
}
