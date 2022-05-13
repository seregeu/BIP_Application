package com.example.bip.presentation.ui.offers.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bip.domain.entity.SelectOffer
import com.example.bip.domain.entity.UserData
import com.example.bip.domain.usecase.GetOffersUseCase
import com.example.bip.domain.usecase.SelectPhotographerUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferHomeViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val selectOffersUseCase: SelectPhotographerUseCase,
) : ViewModel() {
    private val _offerLiveData = MutableLiveData<MutableList<UserData>>()
    val offerLiveData: LiveData<MutableList<UserData>> = _offerLiveData
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _effectLiveData = MutableLiveData<OfferScreenEffect>()
    val effectLiveData: LiveData<OfferScreenEffect> = _effectLiveData

    var orderId: Int = -1

    fun getOffers() {
        getOffersUseCase(orderId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _offerLiveData.value = it.toMutableList() },
                onError = { _effectLiveData.value = OfferScreenEffect.ErrorOffer(it) }
            )
            .addTo(compositeDisposable)
    }

    fun selectPhotographer(userData: UserData, isAccept: Boolean) {
        selectOffersUseCase(SelectOffer(userData, isAccept), orderId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    if (!isAccept) {
                        val currentList = _offerLiveData.value?.toMutableList() ?: mutableListOf()
                        currentList.remove(userData)
                        _offerLiveData.value = currentList
                    }
                    _effectLiveData.value = OfferScreenEffect.SuccessSelectOffer(isAccept = isAccept)
                },
                onError = { _effectLiveData.value = OfferScreenEffect.ErrorOffer(it) }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
