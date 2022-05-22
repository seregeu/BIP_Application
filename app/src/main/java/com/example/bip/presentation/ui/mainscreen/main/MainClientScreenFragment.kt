package com.example.bip.presentation.ui.mainscreen.main

import android.os.Bundle
import com.example.bip.App
import com.example.bip.presentation.ui.mainscreen.BaseMainScreenFragment
import com.example.bip.presentation.ui.mainscreen.elm.Effect
import com.example.bip.presentation.ui.mainscreen.elm.Event
import com.example.bip.presentation.ui.mainscreen.elm.State
import com.example.bip.presentation.ui.orderlist.OrderListFragment
import com.example.bip.presentation.utils.CustomFragmentFactory
import com.example.bip.presentation.utils.FragmentTag
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class MainClientScreenFragment : BaseMainScreenFragment() {

    @Inject
    internal lateinit var mainScreenStore: Store<Event, Effect, State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.mainClientScreenComponent().inject(this)
    }

    override fun notifyOpenAction() {
    }

    override fun moneyOpenAction() {

    }

    override fun createOrderAction() {
        navigateController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.CREATE_ORDER_FRAGMENT))
    }

    override fun offerAction() {
        val bundle = OrderListFragment.createBundle(OrderListFragment.RoutVariant.SELECT_PHOTOGRAPHER)
        navigateController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.ORDER_LIST_FRAGMENT, bundle))
    }

    override fun qrCodeAction() {
        val bundle = OrderListFragment.createBundle(OrderListFragment.RoutVariant.GENERATE_QR_CODE)
        navigateController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.ORDER_LIST_FRAGMENT, bundle = bundle))
    }

    override fun finishOrder() {
        val bundle = OrderListFragment.createBundle(OrderListFragment.RoutVariant.CHECK_PHOTO)
        navigateController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.ORDER_LIST_FRAGMENT, bundle = bundle))
    }

    override val initEvent: Event
        get() = Event.Ui.InitButtonText(resources, false)

    override fun createStore(): Store<Event, Effect, State> = mainScreenStore

    override fun render(state: State) = with(binding) {
        if (!state.isSuccess) return@with
        btnCreteOrder.text = state.configUI.mainButton
        tvQrCode.text = state.configUI.qrCodeAction
        tvAddMoney.text = state.configUI.money
        tvPhotoList.text = state.configUI.anotherButton
    }
}
