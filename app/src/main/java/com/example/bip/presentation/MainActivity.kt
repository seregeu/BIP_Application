package com.example.bip.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bip.R
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.interfaces.NavigateController
import com.example.bip.presentation.utils.CustomFragmentFactory
import com.example.bip.presentation.utils.FragmentTag
import com.example.bip.presentation.utils.addFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), NavigateController, BottomNavigationController {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentFactory = CustomFragmentFactory.create(FragmentTag.AUTH_FRAGMENT_TAG)
            addFragment(fragmentFactory)
        }
        initNavigationListener()
    }

    private fun initNavigationListener() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar)
        /*bottomNavigationView.setOnItemSelectedListener {
            actionBottom(it)
        }*/
    }

    /**
     * Bottom navigation listener action
     * @param item new selected item
    private fun actionBottom(item: MenuItem): Boolean {
    val tag = when (item.itemId) {
    R.id.streamFragment -> FragmentTag.STREAM_FRAGMENT_TAG
    R.id.profileFragment -> FragmentTag.PROFILE_FRAGMENT_TAG
    R.id.peopleFragment -> FragmentTag.PEOPLE_FRAGMENT_TAG
    else -> throw IllegalArgumentException("Unexpected tag")
    }
    navigateFragment(CustomFragmentFactory.create(tag))
    return true
    }*/

    /**
     * set correct checked item for bottom navigation after back press

    private fun setPreviousItem() {
    if (bottomNavigationView.visibility == View.GONE) {
    return
    }
    if (supportFragmentManager.backStackEntryCount <= 1) {
    bottomNavigationView.menu.getItem(MenuItemIdx.PEOPLE_IDX.value).isChecked = true
    return
    }
    if (bottomNavigationView.selectedItemId == R.id.profileFragment) {
    bottomNavigationView.menu.getItem(MenuItemIdx.PEOPLE_IDX.value).isChecked = true
    return
    }
    bottomNavigationView.menu.getItem(MenuItemIdx.PROFILE_IDX.value).isChecked = true
    }
     */

    override fun goneBottomNavigation() {
        bottomNavigationView.visibility = View.GONE
    }

    override fun visibleBottomNavigation() {
        //bottomNavigationView.visibility = View.VISIBLE
    }

    override fun navigateFragment(customFragmentFactory: CustomFragmentFactory) {
        addFragment(customFragmentFactory)
    }
}
