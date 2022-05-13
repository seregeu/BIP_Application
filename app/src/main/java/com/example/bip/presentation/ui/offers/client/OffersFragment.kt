package com.example.bip.presentation.ui.offers.client

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.bip.App
import com.example.bip.presentation.ui.orderlist.OrderListFragment
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.composeutils.theme.themesamples.typography
import com.example.bip.presentation.utils.showToast
import com.example.bip.presentation.utils.viewModels

/**
 * @author v.nasibullin
 */
class OffersFragment : Fragment() {

    private val viewModel: OfferHomeViewModel by viewModels {
        App.appComponent.getOfferHomeViewModel()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
            ComposeCookBookMaterial3Theme(false) {
                Scaffold(
                    topBar = { DatingHomeAppbar() }
                ) {
                    OfferHomeScreen(viewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.orderId = requireArguments().getInt(OrderListFragment.ORDER_ID_KEY)
        viewModel.effectLiveData.observe(viewLifecycleOwner) { effectHandler(it) }
        viewModel.getOffers()
    }

    private fun effectHandler(offerScreenEffect: OfferScreenEffect) {
        when (offerScreenEffect) {
            is OfferScreenEffect.SuccessSelectOffer -> {
                showToast("Фотограф был успещшно выбран")
                requireActivity().onBackPressed()
            }
            is OfferScreenEffect.ErrorOffer -> {
                showToast(offerScreenEffect.error.message)
            }
        }
    }

    companion object {
        fun newInstance(args: Bundle): OffersFragment {
            val fragment = OffersFragment()
            fragment.arguments = args
            return fragment
        }
    }
}


@Composable
fun DatingHomeAppbar() {
    val title = "Discover"
    SmallTopAppBar(
        title = { Text(title, style = typography.h6) },
        actions = {
            IconButton(onClick = {}) {
                Icons.Default.MyLocation
            }
        },
    )
}

fun Fragment.contentView(
    compositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
    context: Context? = getContext(),
    content: @Composable () -> Unit
): ComposeView? {
    context ?: return null
    val view = ComposeView(context)
    view.setViewCompositionStrategy(compositionStrategy)
    view.setContent(content)
    return view
}
