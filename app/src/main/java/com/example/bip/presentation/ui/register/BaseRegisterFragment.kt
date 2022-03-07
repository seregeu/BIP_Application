package com.example.bip.presentation.ui.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bip.databinding.FragmentRegisterBinding
import com.example.bip.presentation.interfaces.NavigateController
import com.example.bip.presentation.ui.register.elm.Effect
import com.example.bip.presentation.ui.register.elm.Event
import com.example.bip.presentation.ui.register.elm.State
import vivid.money.elmslie.android.base.ElmFragment

abstract class BaseRegisterFragment : ElmFragment<Event, Effect, State>() {

    private var _binding: FragmentRegisterBinding? = null
    protected val binding get() = _binding!!
    protected var navigationController: NavigateController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigateController) {
            navigationController = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initErrorRepeat()
        initCreateStreamButton()
        initBackButton()
    }

    override fun onDetach() {
        super.onDetach()
        navigationController = null
    }

    abstract fun initBackButton()
    abstract fun initErrorRepeat()
    abstract fun initCreateStreamButton()
}
