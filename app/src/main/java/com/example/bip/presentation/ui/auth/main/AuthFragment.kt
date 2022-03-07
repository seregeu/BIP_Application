package com.example.bip.presentation.ui.auth.main

import android.os.Bundle
import com.example.bip.App
import com.example.bip.presentation.ui.auth.AuthBaseFragment
import com.example.bip.presentation.ui.auth.elm.Effect
import com.example.bip.presentation.ui.auth.elm.Event
import com.example.bip.presentation.ui.auth.elm.State
import com.example.bip.presentation.utils.CustomFragmentFactory
import com.example.bip.presentation.utils.FragmentTag
import com.example.bip.presentation.utils.showToast
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class AuthFragment : AuthBaseFragment() {

    @Inject
    internal lateinit var authStore: Store<Event, Effect, State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.authComponent().inject(this)
    }

    override val initEvent: Event
        get() = Event.Ui.CheckDatabase

    override fun authAction() {
        val login = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        store.accept(Event.Ui.PressButton(username = login, password = password))
    }

    override fun signUp() {
        navigateController?.navigateFragment(
            customFragmentFactory = CustomFragmentFactory.create(FragmentTag.REGISTER_FRAGMENT_TAG)
        )
    }

    override fun handleEffect(effect: Effect) = when (effect) {
        is Effect.ErrorAuth -> {
            showToast("Something wrong")
        }
        is Effect.SuccessAuth -> {
            showToast("Success auth")
        }
    }

    override fun render(state: State) {}

    override fun createStore(): Store<Event, Effect, State> = authStore
}
