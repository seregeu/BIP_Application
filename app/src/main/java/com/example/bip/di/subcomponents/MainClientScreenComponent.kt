package com.example.bip.di.subcomponents

import com.example.bip.di.MainClientScreenScope
import com.example.bip.di.modules.MainClientScreenModule
import com.example.bip.presentation.ui.mainscreen.main.MainClientScreenFragment
import dagger.Subcomponent

@MainClientScreenScope
@Subcomponent(modules = [MainClientScreenModule::class])
interface MainClientScreenComponent {
    fun inject(mainClientScreenComponent: MainClientScreenFragment)
}
