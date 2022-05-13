package com.example.bip.presentation.ui.qrcode.generate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.usecase.GenerateQrCodeUseCase
import com.example.bip.presentation.ui.qrcode.scan.QrCodeScanEffects
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class GenerateQrCodeViewModel @Inject constructor(
    private val generateQrCodeUseCase: GenerateQrCodeUseCase
) : ViewModel() {

    private val _qrCodeLiveData = MutableLiveData<ByteArray>()
    val qrCodeLiveData: LiveData<ByteArray> = _qrCodeLiveData

    private val _expand = MutableLiveData<Boolean>()
    val expand: LiveData<Boolean> = _expand

    private val _qrCodeScanEffects = MutableLiveData<GenerateQrCodeEffect>()
    val qrCodeScanEffects: LiveData<GenerateQrCodeEffect> = _qrCodeScanEffects

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var coordinatesData: CoordinatesData = CoordinatesData()

    var orderId: Int = 0

    fun setCoordinatesData(coordinatesData: CoordinatesData) {
        this.coordinatesData = coordinatesData
    }

    fun generateQrCode() {
        generateQrCodeUseCase(coordinatesData, orderId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _qrCodeLiveData.value = it
                    _expand.value = true
                },
                onError = {
                    _qrCodeScanEffects.value = GenerateQrCodeEffect.ErrorGenerateQrCode(it)
                }
            )
            .addTo(compositeDisposable)
    }

    fun collapse() {
        _expand.value = false
    }

    fun close() {
        _qrCodeScanEffects.value = GenerateQrCodeEffect.CloseFragment
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
