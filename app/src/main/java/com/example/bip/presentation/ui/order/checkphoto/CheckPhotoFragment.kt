package com.example.bip.presentation.ui.order.checkphoto

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.bip.App
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.interfaces.CheckPhotoController
import com.example.bip.presentation.ui.offers.client.contentView
import com.example.bip.presentation.ui.order.addphoto.AddPhotoViewModel
import com.example.bip.presentation.ui.order.addphoto.CreateOrderScreen
import com.example.bip.presentation.ui.order.client.CreateOrderScreenState
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.showToast
import com.example.bip.presentation.utils.viewModels

/**
 * @author v.nasibullin
 */
class CheckPhotoFragment : Fragment(), CheckPhotoController {

    private var bottomNavigationController: BottomNavigationController? = null

    private val viewModel: CheckPhotoViewModel by viewModels {
        App.appComponent.getCheckPhotoViewModel()
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
                CheckPhotoScreen(this, viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationController?.goneBottomNavigation()
    }

    override fun onDetach() {
        super.onDetach()
        bottomNavigationController = null
    }

    private fun openUrl(link: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))

    override fun checkPhoto(url: String) {
        openUrl(url)
    }

    private fun createOrderState(createOrderScreenState: CreateOrderScreenState) {
        when (createOrderScreenState) {
            is CreateOrderScreenState.SuccessCreateOrder -> {
                showToast("Успешная оплата")
            }
            is CreateOrderScreenState.ErrorCreateOrder -> {
                showToast("Что то пошло не так, попробуйте ещё раз")
            }
        }
    }
}
