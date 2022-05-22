package com.example.bip.presentation.ui.order.addphoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.domain.entity.PhotoData
import com.example.bip.domain.usecase.AddPhotoUseCase
import com.example.bip.domain.usecase.CreateOrderUseCase
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
class AddPhotoViewModel @Inject constructor(
    private val addPhotoUseCase: AddPhotoUseCase
) : ViewModel() {

    private val _createOrderState = MutableLiveData<CreateOrderScreenState>()
    val createOrderState: LiveData<CreateOrderScreenState> = _createOrderState

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var orderId: Int = -1

    fun addPhoto(addPhotoData: PhotoData) {
        addPhotoUseCase(addPhotoData, orderId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { _createOrderState.value = CreateOrderScreenState.SuccessCreateOrder },
                onError = { _createOrderState.value = CreateOrderScreenState.ErrorCreateOrder(it) }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
