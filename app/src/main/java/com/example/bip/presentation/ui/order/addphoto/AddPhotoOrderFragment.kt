package com.example.bip.presentation.ui.order.addphoto

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.bip.App
import com.example.bip.domain.entity.PhotoData
import com.example.bip.presentation.interfaces.AddPhotoController
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.ui.offers.client.contentView
import com.example.bip.presentation.ui.order.client.CreateOrderScreenState
import com.example.bip.presentation.ui.orderlist.OrderListFragment
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.showToast
import com.example.bip.presentation.utils.viewModels

/**
 * @author v.nasibullin
 */
class AddPhotoOrderFragment : Fragment(), AddPhotoController {

    private var bottomNavigationController: BottomNavigationController? = null

    private val viewModel: AddPhotoViewModel by viewModels {
        App.appComponent.getAddPhotoViewModel()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationController) {
            bottomNavigationController = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
            ComposeCookBookMaterial3Theme(false) {
                CreateOrderScreen(this)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.orderId = requireArguments().getInt(OrderListFragment.ORDER_ID_KEY)
        bottomNavigationController?.goneBottomNavigation()
        viewModel.createOrderState.observe(viewLifecycleOwner) { createOrderState(it) }
    }

    override fun onDetach() {
        super.onDetach()
        bottomNavigationController = null
    }

    private fun createOrderState(createOrderScreenState: CreateOrderScreenState) {
        when (createOrderScreenState) {
            is CreateOrderScreenState.SuccessCreateOrder -> {
                showToast("Успешная отправка заказа")
                requireActivity().onBackPressed()
            }
            is CreateOrderScreenState.ErrorCreateOrder -> {
                showToast("Что то пошло не так, попробуйте ещё раз")
            }
        }
    }

    override fun addPhoto(photoData: PhotoData) {
        viewModel.addPhoto(photoData)
    }

    companion object {
        fun newInstance(args: Bundle): AddPhotoOrderFragment {
            val fragment = AddPhotoOrderFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
