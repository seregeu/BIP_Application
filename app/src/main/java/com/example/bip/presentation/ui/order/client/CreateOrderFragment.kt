package com.example.bip.presentation.ui.order.client

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
import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.interfaces.CreateOrderController
import com.example.bip.presentation.ui.offers.client.DatingHomeAppbar
import com.example.bip.presentation.ui.offers.client.contentView
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.showToast
import com.example.bip.presentation.utils.viewModels

/**
 * @author v.nasibullin
 */
class CreateOrderFragment : Fragment(), CreateOrderController {

    private var bottomNavigationController: BottomNavigationController? = null

    private val viewModel: CreateOrderViewModel by viewModels {
        App.appComponent.getCreateOrderViewModel()
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
                    topBar = { DatingHomeAppbar("Создание заказа") }
                ) {
                    CreateOrderScreen(this)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationController?.goneBottomNavigation()
        viewModel.createOrderState.observe(viewLifecycleOwner) { createOrderState(it) }
    }

    override fun onDetach() {
        super.onDetach()
        bottomNavigationController = null
    }

    override fun createOrder(createOrderData: CreateOrderData) {
        viewModel.createOrder(createOrderData)
    }

    private fun createOrderState(createOrderScreenState: CreateOrderScreenState) {
        when (createOrderScreenState) {
            is CreateOrderScreenState.SuccessCreateOrder -> {
                showToast("Успешное создание заказа")
                requireActivity().onBackPressed()
            }
            is CreateOrderScreenState.ErrorCreateOrder -> {
                showToast("Что то пошло не так, попробуйте ещё раз")
            }
        }
    }
}
