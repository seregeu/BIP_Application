package com.example.bip.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.bip.R

fun AppCompatActivity.addFragmentWithoutBackstack(customFragmentFactory: CustomFragmentFactory) {
    if (supportFragmentManager.backStackEntryCount != 0) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    supportFragmentManager.beginTransaction()
        .replace(
            R.id.nav_host_fragment,
            customFragmentFactory.fragment,
            customFragmentFactory.fragmentTag.value
        )
        .commit()
}

fun AppCompatActivity.addFragment(customFragmentFactory: CustomFragmentFactory) {
    if (
        customFragmentFactory.fragmentTag == FragmentTag.AUTH_FRAGMENT_TAG ||
        customFragmentFactory.fragmentTag == FragmentTag.REGISTER_FRAGMENT_TAG ||
        customFragmentFactory.fragmentTag == FragmentTag.MAIN_CLIENT_SCREEN_FRAGMENT ||
        customFragmentFactory.fragmentTag == FragmentTag.MAIN_CLIENT_SCREEN_FRAGMENT
    ) {
        addFragmentWithoutBackstack(customFragmentFactory = customFragmentFactory)
        return
    }
    val tag = customFragmentFactory.fragmentTag
    supportFragmentManager.popBackStack(tag.value, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    supportFragmentManager.beginTransaction()
        .replace(R.id.nav_host_fragment, customFragmentFactory.fragment, tag.value)
        .addToBackStack(tag.value)
        .commit()

}
