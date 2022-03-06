package com.example.bip.presentation.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bip.presentation.ui.auth.main.AuthFragment

class CustomFragmentFactory(var fragment: Fragment, var fragmentTag: FragmentTag) {
    companion object {
        fun create(fragmentTag: FragmentTag, bundle: Bundle = Bundle()): CustomFragmentFactory {
            val fragment = when (fragmentTag) {
                FragmentTag.AUTH_FRAGMENT_TAG -> AuthFragment()
            }
            return CustomFragmentFactory(fragment, fragmentTag)
        }
    }
}
