package com.example.bip.presentation.ui.qrcode.generate

/**
 * @author v.nasibullin
 */
sealed class GenerateQrCodeEffect {

    object CloseFragment : GenerateQrCodeEffect()
    class GenerateFragment : GenerateQrCodeEffect()
}
