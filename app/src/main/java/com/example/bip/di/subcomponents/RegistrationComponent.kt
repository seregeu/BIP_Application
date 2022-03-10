package com.example.bip.di.subcomponents

import com.example.bip.di.RegisterScope
import com.example.bip.di.modules.RegisterModule
import com.example.bip.presentation.ui.register.main.RegisterFragment
import dagger.Subcomponent

@RegisterScope
@Subcomponent(modules = [RegisterModule::class])
interface RegistrationComponent {
    fun inject(registerFragment: RegisterFragment)
}
