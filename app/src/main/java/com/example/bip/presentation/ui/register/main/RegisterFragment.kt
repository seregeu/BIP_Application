package com.example.bip.presentation.ui.register.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.bip.App
import com.example.bip.domain.entity.UserData
import com.example.bip.presentation.ui.register.BaseRegisterFragment
import com.example.bip.presentation.ui.register.elm.Effect
import com.example.bip.presentation.ui.register.elm.Event
import com.example.bip.presentation.ui.register.elm.State
import com.example.bip.presentation.utils.*
import com.example.bip.presentation.utils.validation.InputTextWatcher
import com.example.bip.presentation.utils.validation.ValidatorType
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class RegisterFragment : BaseRegisterFragment(){

    private lateinit var userData: UserData

    @Inject
    internal lateinit var subscribeStreamStore: Store<Event, Effect, State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.registerComponent().inject(this)
    }

    override fun initBackButton() {
        binding.btnBack.setOnClickListener {
            navigationController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.AUTH_FRAGMENT_TAG))
        }
    }

    override fun initErrorRepeat() {
        binding.errorContent.tvRepeat.setOnClickListener {
            store.accept(Event.Ui.RegisterUser(userData))
        }
    }

    override fun initCreateStreamButton() {
        binding.btnCreate.apply {
            setOnClickListener {
                userData = getDataFromFields()
                store.accept(Event.Ui.RegisterUser(userData))
            }
            isClickable = false
        }
    }

    override val initEvent: Event
        get() = Event.Ui.RegisterUser(UserData.Builder().build())

    override fun createStore(): Store<Event, Effect, State> = subscribeStreamStore

    /**
     * show error layout
     */
    private fun showErrorScreen() {
        with(binding) {
            svCreateNewStream.visibility = View.GONE
            nsvErrorConnection.visibility = View.VISIBLE
        }
    }

    override fun render(state: State) {
        if (state.isError) {
            showToast(state.error?.message)
        }
        if (state.item is Boolean) {
            if (!state.item) {
                showErrorScreen()
            }
        }
    }

    override fun handleEffect(effect: Effect) = when (effect) {
        is Effect.ErrorRegister -> {
            effect
            showToast("Something went wrong")
        }
        is Effect.SuccessRegister -> {
            navigationController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.AUTH_FRAGMENT_TAG))
        }
    }

    override fun initDataFieldsListeners() {
        val btnCreate = binding.btnCreate
        binding.etFirstName.addValidatorTextWatchListener(ValidatorType.TYPE_FIRST_NAME, btnCreate)
        binding.etSecondName.addValidatorTextWatchListener(ValidatorType.TYPE_SECOND_NAME, btnCreate)
        binding.etAvatarUrl.addValidatorTextWatchListener(ValidatorType.TYPE_AVATAR_URL, btnCreate)
        binding.etPhoneNumber.addValidatorTextWatchListener(ValidatorType.TYPE_PHONE_NUMBER, btnCreate)
        binding.etMail.addValidatorTextWatchListener(ValidatorType.TYPE_MAIL, btnCreate)
        binding.etUsername.addValidatorTextWatchListener(ValidatorType.TYPE_USERNAME, btnCreate)
        binding.etPassword.addValidatorTextWatchListener(ValidatorType.TYPE_PASSWORD, btnCreate)
    }

    private fun getDataFromFields(): UserData {
        return binding.run {
            UserData.Builder()
                .firstName(etFirstName.text.toString())
                .secondName(etSecondName.text.toString())
                .isPhotographer(radioYes.isChecked)
                .avatarUrl(etAvatarUrl.text.toString())
                .phoneNumber(etPhoneNumber.text.toString())
                .mail(etMail.text.toString())
                .username(etUsername.text.toString())
                .password(etPassword.text.toString())
                .build()
        }
    }
}
