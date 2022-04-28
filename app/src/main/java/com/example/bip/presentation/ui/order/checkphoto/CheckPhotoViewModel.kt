package com.example.bip.presentation.ui.order.checkphoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.usecase.FinishOrderUseCase
import com.example.bip.domain.usecase.GetPreviewUseCase
import com.example.bip.presentation.ui.order.client.CreateOrderScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class CheckPhotoViewModel @Inject constructor(
    private val getPreviewUseCase: GetPreviewUseCase,
    private val finishOrderUseCase: FinishOrderUseCase,
) : ViewModel() {

    private val _photoUrl = MutableLiveData<String>()
    val photoUrl: LiveData<String> = _photoUrl

    private val _createOrderState = MutableLiveData<CreateOrderScreenState>()
    val createOrderState: LiveData<CreateOrderScreenState> = _createOrderState

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        getPreviewPhoto()
    }

    private fun getPreviewPhoto() {
        getPreviewUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _photoUrl.value = it },
                onError = {
                    it.message
                }
            )
            .addTo(compositeDisposable)
    }

    fun finishOrder() {
        finishOrderUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _photoUrl.value = it
                    _createOrderState.value = CreateOrderScreenState.SuccessCreateOrder
                },
                onError = {
                    _createOrderState.value = CreateOrderScreenState.ErrorCreateOrder(it)
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
