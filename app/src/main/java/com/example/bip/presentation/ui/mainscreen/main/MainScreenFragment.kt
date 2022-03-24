package com.example.bip.presentation.ui.mainscreen.main

import android.os.Bundle
import com.example.bip.App
import com.example.bip.presentation.ui.mainscreen.BaseMainScreenFragment
import com.example.bip.presentation.ui.mainscreen.elm.Effect
import com.example.bip.presentation.ui.mainscreen.elm.Event
import com.example.bip.presentation.ui.mainscreen.elm.State
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class MainScreenFragment : BaseMainScreenFragment() {
    @Inject
    internal lateinit var mainScreenStore: Store<Event, Effect, State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.mainScreenComponent().inject(this)
    }

    override fun notifyOpenAction() {
        TODO("Not yet implemented")
    }

    override fun moneyOpenAction() {
        TODO("Not yet implemented")
    }

    override fun createOrderAction() {
        TODO("Not yet implemented")
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

    override fun render(state: State) {
    }

    override fun handleEffect(effect: Effect) = when (effect) {
        is Effect.InitButtonText -> with(binding) {
            btnCreteOrder.text = effect.buttonTexts.firstOrNull()
            tvQrCode.text = effect.buttonTexts[1]
            tvAddMoney.text = effect.buttonTexts[2]
        }
    }
}