package com.example.bip.di.subcomponents

import com.example.bip.di.MainPhotographerScope
import com.example.bip.di.modules.MainPhotographScreenModule
import com.example.bip.presentation.ui.mainscreen.main.MainPhotographerScreenFragment
import dagger.Subcomponent

@MainPhotographerScope
@Subcomponent(modules = [MainPhotographScreenModule::class])
interface MainPhotographerScreenComponent {
    fun inject(mainPhotographerScreenFragment: MainPhotographerScreenFragment)
}
