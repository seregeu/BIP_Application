package com.example.bip.presentation.ui.auth.main

import android.os.Bundle
import android.view.View
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
        store.accept(Event.Ui.PressAuthButton(username = login, password = password))
    }

    override fun signUp() {
        navigateController?.navigateFragment(
            customFragmentFactory = CustomFragmentFactory.create(FragmentTag.REGISTER_FRAGMENT_TAG)
        )
    }

    override fun auth2FaAction() {
        val code: String = binding.et2factor.text.toString()
        store.accept(Event.Ui.PressAuth2FaButton(code))
    }

    override fun handleEffect(effect: Effect) = when (effect) {
        is Effect.ErrorAuth -> {
            showToast(effect.error.message)
        }
        Effect.SuccessAuthToken -> {
            store.accept(Event.Ui.CheckDatabase)
        }
        is Effect.SuccessGetUserData -> {
            val needFragmentTag = if (effect.userData.isPhotographer) {
                FragmentTag.MAIN_PHOTOGRAPHER_SCREEN_FRAGMENT
            } else {
                FragmentTag.MAIN_CLIENT_SCREEN_FRAGMENT
            }
            navigateController?.navigateFragment(CustomFragmentFactory.create(needFragmentTag))
        }
        Effect.SuccessNonAuthToken -> {
            with(binding) {
                btnAuth.visibility = View.GONE
                btnLogin.visibility = View.GONE
                etPassword.visibility = View.GONE
                etUsername.visibility = View.GONE
                et2factor.visibility = View.VISIBLE
                btn2Auth.visibility = View.VISIBLE
            }
        }
    }

    override fun render(state: State) {}

    override fun createStore(): Store<Event, Effect, State> = authStore
}
