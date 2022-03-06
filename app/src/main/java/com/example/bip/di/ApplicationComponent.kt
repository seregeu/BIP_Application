package com.example.bip.di

import android.content.Context
import com.example.bip.di.modules.DatabaseModule
import com.example.bip.di.modules.NetworkModule
import com.example.bip.di.modules.RepositoryModule
import com.example.bip.di.modules.UseCaseModule
import com.example.bip.di.subcomponents.AuthComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, RepositoryModule::class, UseCaseModule::class, DatabaseModule::class]
)
interface ApplicationComponent {

    fun authComponent(): AuthComponent

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): ApplicationComponent
    }

    fun context(): Context
}
