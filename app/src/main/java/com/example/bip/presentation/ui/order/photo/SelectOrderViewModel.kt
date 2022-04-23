package com.example.bip.presentation.ui.order.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.usecase.GetBacklogOrders
import com.example.bip.domain.usecase.SelectOrderUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class SelectOrderViewModel @Inject constructor(
    private val getBacklogOrders: GetBacklogOrders,
    private val selectOrderUseCase: SelectOrderUseCase,
) : ViewModel() {

    private val _effectLiveData = MutableLiveData<SelectOrderScreenState>()
    val effectLiveData: LiveData<SelectOrderScreenState> = _effectLiveData

    private val _orderLiveData = MutableLiveData<MutableList<OrderData>>()
    val orderLiveData: LiveData<MutableList<OrderData>> = _orderLiveData

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        getOrders()
    }

    fun getOrders() {
        getBacklogOrders()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _orderLiveData.value = it.toMutableList() },
                onError = { _effectLiveData.value = SelectOrderScreenState.ErrorSelectOrder(it) }
            )
            .addTo(compositeDisposable)
    }

    fun selectOrder(orderData: OrderData) {
        selectOrderUseCase(orderData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { _effectLiveData.value = SelectOrderScreenState.SuccessSelectOrder },
                onError = { _effectLiveData.value = SelectOrderScreenState.ErrorSelectOrder(it) }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
