package com.example.bip.di.subcomponents

import com.example.bip.di.MainScreenScope
import com.example.bip.di.modules.MainScreenModule
import com.example.bip.presentation.ui.mainscreen.main.MainScreenFragment
import dagger.Subcomponent

@MainScreenScope
@Subcomponent(modules = [MainScreenModule::class])
interface MainScreenComponent {
    fun inject(mainScreenFragment: MainScreenFragment)
}