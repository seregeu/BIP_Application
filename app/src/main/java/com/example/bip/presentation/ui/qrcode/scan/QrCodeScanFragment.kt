package com.example.bip.presentation.ui.qrcode.scan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.bip.App
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.ui.offers.client.contentView
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.showToast
import com.example.bip.presentation.utils.viewModels

/**
 * @author v.nasibullin
 */
class QrCodeScanFragment : Fragment() {

    private var bottomNavigationController: BottomNavigationController? = null

    private val viewModel: QrCodeScanViewModel by viewModels {
        App.appComponent.getQrCodeScanViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationController) {
            bottomNavigationController = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
            ComposeCookBookMaterial3Theme {
                CameraXCompose(viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationController?.goneBottomNavigation()
        viewModel.qrCodeScanEffects.observe(viewLifecycleOwner) { handleEffect(it) }
    }

    private fun handleEffect(qrCodeScanEffects: QrCodeScanEffects) {
        when (qrCodeScanEffects) {
            is QrCodeScanEffects.QrCodeSuccessConfirm -> {
                showToast("Всё круто! Начисляем деньги")
                showToast("Хорошо пофоткаться")
            }
            is QrCodeScanEffects.QrCodeErrorConfirm -> {
                showToast("Что-то не то, попробуйте ещё раз")
                showToast(qrCodeScanEffects.throwable.message)
            }
        }
    }
}
