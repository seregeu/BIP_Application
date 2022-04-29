package com.example.bip.presentation.ui.qrcode.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.usecase.ConfirmQrCodeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class QrCodeScanViewModel @Inject constructor(
    private val confirmQrCodeUseCase: ConfirmQrCodeUseCase
) : ViewModel() {

    private val _qrCodeScanEffects = MutableLiveData<QrCodeScanEffects>()
    val qrCodeScanEffects: LiveData<QrCodeScanEffects> = _qrCodeScanEffects

    private val qrCodeSubject: PublishSubject<String> = PublishSubject.create()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var coordinatesData: CoordinatesData = CoordinatesData()

    init {
        subscribeToGetQrCode()
    }

    fun setCoordinatesData(coordinatesData: CoordinatesData) {
        this.coordinatesData = coordinatesData
    }

    fun findQrCode(code: String) {
        qrCodeSubject.onNext(code)
    }

    private fun subscribeToGetQrCode() {
        qrCodeSubject
            .subscribeOn(Schedulers.io())
            .debounce(1, TimeUnit.SECONDS, Schedulers.io())
            .switchMapCompletable { code ->
                confirmQrCodeUseCase(code, coordinatesData)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { _qrCodeScanEffects.value = QrCodeScanEffects.QrCodeSuccessConfirm },
                onError = {
                    _qrCodeScanEffects.value = QrCodeScanEffects.QrCodeErrorConfirm(it)
                    subscribeToGetQrCode()
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
