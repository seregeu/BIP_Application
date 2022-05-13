package com.example.bip.presentation.ui.orderlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.usecase.GetOrdersWithType
import com.example.bip.domain.usecase.GetPhotographerOrder
import com.example.bip.domain.usecase.OrderType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class OrderListViewModel @Inject constructor(
    private val getOrdersWithType: GetOrdersWithType,
    private val getPhotographerOrder: GetPhotographerOrder,
) : ViewModel() {

    private val _effectLiveData = MutableLiveData<SelectOrderScreenState>()
    val effectLiveData: LiveData<SelectOrderScreenState> = _effectLiveData

    private val _orderLiveData = MutableLiveData<MutableList<OrderData>>()
    val orderLiveData: LiveData<MutableList<OrderData>> = _orderLiveData

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getOrders(orderType: OrderType) {
        getOrdersWithType(orderType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _orderLiveData.value = it.toMutableList() },
                onError = { _effectLiveData.value = SelectOrderScreenState.ErrorSelectOrder(it) }
            )
            .addTo(compositeDisposable)
    }

    fun getPhotographer() {
        getPhotographerOrder()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _orderLiveData.value = it.toMutableList() },
                onError = { _effectLiveData.value = SelectOrderScreenState.ErrorSelectOrder(it) }
            )
            .addTo(compositeDisposable)
    }
}
