package com.example.bip.presentation.ui.orderlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.bip.App
import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.usecase.OrderType
import com.example.bip.presentation.interfaces.NavigateController
import com.example.bip.presentation.interfaces.OrderListController
import com.example.bip.presentation.ui.offers.client.contentView
import com.example.bip.presentation.utils.CustomFragmentFactory
import com.example.bip.presentation.utils.FragmentTag
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.viewModels

/**
 * @author v.nasibullin
 */
class OrderListFragment : Fragment(), OrderListController {

    private var navigateController: NavigateController? = null

    private lateinit var routVariant: RoutVariant

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigateController) {
            navigateController = context
        }
    }

    private val viewModel: OrderListViewModel by viewModels {
        App.appComponent.getActiveOrderListViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
            ComposeCookBookMaterial3Theme(false) {
                OrderListScreen(viewModel.orderLiveData, orderListController = this)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        routVariant = RoutVariant.getRouteVariantFromInt(requireArguments().getInt(NEXT_FRAGMENT_KEY))
        getOrders()
    }

    private fun getOrders() {
        when (routVariant) {
            RoutVariant.GENERATE_QR_CODE, RoutVariant.CHECK_PHOTO -> viewModel.getOrders(orderType = OrderType.ACTIVE)
            RoutVariant.SELECT_PHOTOGRAPHER -> viewModel.getOrders(orderType = OrderType.BACKLOG)
            RoutVariant.ADD_PHOTO -> viewModel.getPhotographer()
        }

    }

    override fun onDetach() {
        super.onDetach()
        navigateController = null
    }

    override fun orderSelect(orderData: OrderData) {
        routeFragment(orderData.id)
    }

    private fun routeFragment(orderId: Int) {
        val bundle = createBundle(orderId)
        val needTag = when (routVariant) {
            RoutVariant.GENERATE_QR_CODE -> FragmentTag.QRCODE_GENERATE_FRAGMENT
            RoutVariant.SELECT_PHOTOGRAPHER -> FragmentTag.NOTIFICATION_CLIENT_SCREEN_FRAGMENT
            RoutVariant.CHECK_PHOTO -> FragmentTag.CHECK_PHOTO_FRAGMENT
            RoutVariant.ADD_PHOTO -> FragmentTag.ADD_PHOTO_FRAGMENT
        }
        navigateController?.navigateFragment(CustomFragmentFactory.create(needTag, bundle))
    }

    enum class RoutVariant(val idx: Int) {
        GENERATE_QR_CODE(idx = 0),
        SELECT_PHOTOGRAPHER(idx = 1),
        CHECK_PHOTO(idx = 2),
        ADD_PHOTO(idx = 3);

        companion object {
            fun getRouteVariantFromInt(idx: Int): RoutVariant {
                if (idx < values().first().idx || idx > values().last().idx) {
                    throw IllegalArgumentException()
                }
                return values().first { it.idx == idx }
            }
        }
    }

    companion object {
        private const val NEXT_FRAGMENT_KEY = "nextFragmentKey"
        const val ORDER_ID_KEY = "orderKeyKey"

        fun createBundle(id: Int): Bundle {
            return Bundle().apply { putInt(ORDER_ID_KEY, id) }
        }

        fun createBundle(routVariant: RoutVariant): Bundle {
            return Bundle().apply { putInt(NEXT_FRAGMENT_KEY, routVariant.idx) }
        }

        fun newInstance(args: Bundle): OrderListFragment {
            val fragment = OrderListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
