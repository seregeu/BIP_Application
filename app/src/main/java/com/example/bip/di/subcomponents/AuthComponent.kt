package com.example.bip.di.subcomponents

import com.example.bip.di.AuthScope
import com.example.bip.di.modules.AuthModule
import com.example.bip.presentation.ui.auth.main.AuthFragment
import dagger.Subcomponent

@AuthScope
@Subcomponent(modules = [AuthModule::class])
interface AuthComponent {
    fun inject(authFragment: AuthFragment)
}
