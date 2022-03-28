package com.example.bip.di

import android.content.Context
import com.example.bip.di.modules.DatabaseModule
import com.example.bip.di.modules.NetworkModule
import com.example.bip.di.modules.RepositoryModule
import com.example.bip.di.modules.UseCaseModule
import com.example.bip.di.subcomponents.AuthComponent
import com.example.bip.di.subcomponents.MainClientScreenComponent
import com.example.bip.di.subcomponents.MainPhotographerScreenComponent
import com.example.bip.di.subcomponents.RegistrationComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, RepositoryModule::class, UseCaseModule::class, DatabaseModule::class]
)
interface ApplicationComponent {

    fun authComponent(): AuthComponent

    fun registerComponent(): RegistrationComponent

    fun mainClientScreenComponent(): MainClientScreenComponent

    fun mainPhotographerScreenComponent(): MainPhotographerScreenComponent

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): ApplicationComponent
    }

    fun context(): Context
}
