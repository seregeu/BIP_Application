package com.example.bip.domain.usecase

import com.example.bip.domain.repository.QrCodeRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface ConfirmQrCodeUseCase : (String) -> (Completable) {
    override fun invoke(qrcode: String): Completable
}

class ConfirmQrCodeUseCaseImpl @Inject constructor(
    private val qrCodeRepository: QrCodeRepository
) : ConfirmQrCodeUseCase {

    override fun invoke(qrcode: String): Completable {
        return qrCodeRepository.checkQrCode(qrcode)
    }
}
