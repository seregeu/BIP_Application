package com.example.bip.presentation.ui.qrcode.scan

/**
 * @author v.nasibullin
 */
sealed class QrCodeScanEffects {

    object QrCodeSuccessConfirm : QrCodeScanEffects()

    class QrCodeErrorConfirm(val throwable: Throwable) : QrCodeScanEffects()
}
