package com.example.bip.domain.usecase

import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.repository.QrCodeRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface GenerateQrCodeUseCase : (CoordinatesData) -> (Single<ByteArray>) {
    override fun invoke(coordinatesData: CoordinatesData): Single<ByteArray>
}

class GenerateQrCodeUseCaseImpl @Inject constructor(
    private val qrCodeRepository: QrCodeRepository
) : GenerateQrCodeUseCase {

    override fun invoke(coordinatesData: CoordinatesData): Single<ByteArray> {
        return qrCodeRepository.generateQrCode(coordinatesData)
    }
}
