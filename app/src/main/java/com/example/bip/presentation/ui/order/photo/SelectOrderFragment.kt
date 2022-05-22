package com.example.bip.presentation.ui.order.photo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.bip.App
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.ui.offers.client.DatingHomeAppbar
import com.example.bip.presentation.ui.offers.client.contentView
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.showToast
import com.example.bip.presentation.utils.viewModels

/**
 * @author v.nasibullin
 */
class SelectOrderFragment : Fragment() {

    private var bottomNavigationController: BottomNavigationController? = null

    private val viewModel: SelectOrderViewModel by viewModels {
        App.appComponent.getSelectOrderViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationController) {
            bottomNavigationController = context
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
            ComposeCookBookMaterial3Theme(false) {
                Scaffold(
                    topBar = { DatingHomeAppbar("Выберите заказ") }
                ) {
                    SelectOrderScreen(viewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationController?.goneBottomNavigation()
        viewModel.effectLiveData.observe(viewLifecycleOwner) { effect(it) }
    }

    override fun onDetach() {
        super.onDetach()
        bottomNavigationController = null
    }

    private fun effect(selectOrderScreenState: SelectOrderScreenState) {
        when (selectOrderScreenState) {
            is SelectOrderScreenState.SuccessSelectOrder -> {
                showToast("Успешно выбрали заказ")
                requireActivity().onBackPressed()
            }
            is SelectOrderScreenState.ErrorSelectOrder -> {
                showToast("Что-то пошло не так. Попробуйте ещё раз")
                showToast(selectOrderScreenState.error.message)
            }
        }
    }
}
