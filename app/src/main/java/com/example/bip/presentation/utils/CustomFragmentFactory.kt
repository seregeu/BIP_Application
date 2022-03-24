package com.example.bip.presentation.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bip.presentation.ui.auth.main.AuthFragment
import com.example.bip.presentation.ui.mainscreen.main.MainScreenFragment
import com.example.bip.presentation.ui.register.main.RegisterFragment

class CustomFragmentFactory(var fragment: Fragment, var fragmentTag: FragmentTag) {
    companion object {
        fun create(fragmentTag: FragmentTag, bundle: Bundle = Bundle()): CustomFragmentFactory {
            val fragment = when (fragmentTag) {
                FragmentTag.AUTH_FRAGMENT_TAG -> AuthFragment()
                FragmentTag.REGISTER_FRAGMENT_TAG -> RegisterFragment()
                FragmentTag.MAIN_SCREEN_FRAGMENT -> MainScreenFragment()
            }
            return CustomFragmentFactory(fragment, fragmentTag)
        }
    }
}
