package com.example.bip.presentation.ui.mainscreen.main

import android.os.Bundle
import com.example.bip.App
import com.example.bip.presentation.ui.mainscreen.BaseMainScreenFragment
import com.example.bip.presentation.ui.mainscreen.elm.Effect
import com.example.bip.presentation.ui.mainscreen.elm.Event
import com.example.bip.presentation.ui.mainscreen.elm.State
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
        navigateController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.NOTIFICATION_CLIENT_SCREEN_FRAGMENT))
    }

    override fun moneyOpenAction() {
        TODO("Not yet implemented")
    }

    override fun createOrderAction() {
        navigateController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.CREATE_ORDER_FRAGMENT))
    }

    override fun qrCodeAction() {
        TODO("Not yet implemented")
    }

    override fun moneyTransitAction() {
        TODO("Not yet implemented")
    }

    override val initEvent: Event
        get() = Event.Ui.InitButtonText(resources, false)

    override fun createStore(): Store<Event, Effect, State> = mainScreenStore

    override fun render(state: State) = with(binding) {
        if (!state.isSuccess) return@with
        btnCreteOrder.text = state.configUI.mainButton
        tvQrCode.text = state.configUI.qrCodeAction
        tvAddMoney.text = state.configUI.money
    }
}
