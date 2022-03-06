package com.example.bip.presentation.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bip.databinding.FragmentAuthBinding
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.interfaces.NavigateController
import com.example.bip.presentation.ui.auth.elm.Effect
import com.example.bip.presentation.ui.auth.elm.Event
import com.example.bip.presentation.ui.auth.elm.State
import vivid.money.elmslie.android.base.ElmFragment


abstract class AuthBaseFragment : ElmFragment<Event, Effect, State>() {

    protected var navigateController: NavigateController? = null
    protected val binding get() = _binding!!
    private var bottomNavigationController: BottomNavigationController? = null
    private var _binding: FragmentAuthBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationController) {
            bottomNavigationController = context
            bottomNavigationController?.goneBottomNavigation()
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
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.btnLogin.setOnClickListener {
            authAction()
        }
    }

    internal abstract fun authAction()

    override fun onDetach() {
        super.onDetach()
        bottomNavigationController = null
        navigateController = null
    }
}
