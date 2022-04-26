package com.example.bip.presentation.ui.qrcode.scan

import android.annotation.SuppressLint
import android.graphics.ImageFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class QrCodeAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    @RequiresApi(Build.VERSION_CODES.M)
    private val supportedImageFormats = listOf(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888,
    )


    private val detector = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    )

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        if (imageProxy.format in supportedImageFormats) {
            val inputImage = InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)
            detector.process(inputImage).addOnSuccessListener { barcodes ->
                barcodes.firstOrNull()?.let {
                    onQrCodeScanned(it.rawValue ?: "")
                }
            }.addOnCompleteListener {
                imageProxy.close()
            }
        }
    }
}

