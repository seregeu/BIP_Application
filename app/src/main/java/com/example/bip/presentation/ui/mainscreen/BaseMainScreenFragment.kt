package com.example.bip.presentation.ui.mainscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bip.databinding.FragmentMainScreenBinding
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.interfaces.NavigateController
import com.example.bip.presentation.ui.mainscreen.elm.Effect
import com.example.bip.presentation.ui.mainscreen.elm.Event
import com.example.bip.presentation.ui.mainscreen.elm.State
import vivid.money.elmslie.android.base.ElmFragment

abstract class BaseMainScreenFragment : ElmFragment<Event, Effect, State>() {

    private var bottomNavigationController: BottomNavigationController? = null
    protected var navigateController: NavigateController? = null
    private var _binding: FragmentMainScreenBinding? = null


    protected val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationController) {
            bottomNavigationController = context
            bottomNavigationController?.visibleBottomNavigation()
        }
        if (context is NavigateController) {
            navigateController = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() = with(binding) {
        imgNotify.setOnClickListener { notifyOpenAction() }
        getMoneyButton.setOnClickListener { moneyOpenAction() }
        btnCreteOrder.setOnClickListener { createOrderAction() }
        cvQrCode.setOnClickListener { qrCodeAction() }
        cvAddMoney.setOnClickListener { moneyTransitAction() }
    }

    abstract fun notifyOpenAction()
    abstract fun moneyOpenAction()
    abstract fun createOrderAction()
    abstract fun qrCodeAction()
    abstract fun moneyTransitAction()
}