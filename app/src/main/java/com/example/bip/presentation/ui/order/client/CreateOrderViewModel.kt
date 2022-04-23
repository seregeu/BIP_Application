package com.example.bip.presentation.ui.order.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.domain.usecase.CreateOrderUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class CreateOrderViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase
): ViewModel() {

    private val _createOrderState = MutableLiveData<CreateOrderScreenState>()
    val createOrderState: LiveData<CreateOrderScreenState> = _createOrderState

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun createOrder(createOrderData: CreateOrderData){
        createOrderUseCase(createOrderData =  createOrderData)
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
