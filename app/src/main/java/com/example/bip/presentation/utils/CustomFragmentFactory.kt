package com.example.bip.presentation.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bip.presentation.ui.auth.main.AuthFragment
import com.example.bip.presentation.ui.mainscreen.main.MainClientScreenFragment
import com.example.bip.presentation.ui.mainscreen.main.MainPhotographerScreenFragment
import com.example.bip.presentation.ui.offers.client.OffersFragment
import com.example.bip.presentation.ui.order.addphoto.AddPhotoOrderFragment
import com.example.bip.presentation.ui.order.checkphoto.CheckPhotoFragment
import com.example.bip.presentation.ui.order.client.CreateOrderFragment
import com.example.bip.presentation.ui.order.photo.SelectOrderFragment
import com.example.bip.presentation.ui.orderlist.OrderListFragment
import com.example.bip.presentation.ui.qrcode.generate.GenerateQrCode
import com.example.bip.presentation.ui.qrcode.generate.GenerateQrCodeFragment
import com.example.bip.presentation.ui.qrcode.scan.QrCodeScanFragment
import com.example.bip.presentation.ui.register.main.RegisterFragment

class CustomFragmentFactory(var fragment: Fragment, var fragmentTag: FragmentTag) {
    companion object {
        fun create(fragmentTag: FragmentTag, bundle: Bundle = Bundle()): CustomFragmentFactory {
            val fragment = when (fragmentTag) {
                FragmentTag.AUTH_FRAGMENT_TAG -> AuthFragment()
                FragmentTag.REGISTER_FRAGMENT_TAG -> RegisterFragment()
                FragmentTag.MAIN_CLIENT_SCREEN_FRAGMENT -> MainClientScreenFragment()
                FragmentTag.MAIN_PHOTOGRAPHER_SCREEN_FRAGMENT -> MainPhotographerScreenFragment()
                FragmentTag.NOTIFICATION_CLIENT_SCREEN_FRAGMENT -> OffersFragment.newInstance(bundle)
                FragmentTag.CREATE_ORDER_FRAGMENT -> CreateOrderFragment()
                FragmentTag.SELECT_ORDER_FRAGMENT -> SelectOrderFragment()
                FragmentTag.QRCODE_SCAN_FRAGMENT -> QrCodeScanFragment()
                FragmentTag.QRCODE_GENERATE_FRAGMENT -> GenerateQrCodeFragment.newInstance(bundle)
                FragmentTag.ADD_PHOTO_FRAGMENT -> AddPhotoOrderFragment.newInstance(bundle)
                FragmentTag.CHECK_PHOTO_FRAGMENT -> CheckPhotoFragment.newInstance(bundle)
                FragmentTag.ORDER_LIST_FRAGMENT -> OrderListFragment.newInstance(bundle)
            }
            return CustomFragmentFactory(fragment, fragmentTag)
        }
    }
}
