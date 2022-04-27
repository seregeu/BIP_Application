package com.example.bip.presentation.ui.qrcode.generate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.domain.usecase.GenerateQrCodeUseCase
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

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var coordinatesData: CoordinatesData = CoordinatesData()

    fun setCoordinatesData(coordinatesData: CoordinatesData) {
        this.coordinatesData = coordinatesData
    }

    fun generateQrCode() {
        generateQrCodeUseCase(coordinatesData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _qrCodeLiveData.value = it
                    _expand.value = true
                },
                onError = {
                    it.message
                }
            )
            .addTo(compositeDisposable)
    }

    fun collapse(){
        _expand.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
