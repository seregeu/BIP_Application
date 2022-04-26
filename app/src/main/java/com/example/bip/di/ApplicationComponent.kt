package com.example.bip.di

import android.content.Context
import com.example.bip.di.modules.DatabaseModule
import com.example.bip.di.modules.NetworkModule
import com.example.bip.di.modules.RepositoryModule
import com.example.bip.di.modules.UseCaseModule
import com.example.bip.di.subcomponents.*
import com.example.bip.presentation.ui.offers.client.OfferHomeViewModel
import com.example.bip.presentation.ui.order.client.CreateOrderViewModel
import com.example.bip.presentation.ui.order.photo.SelectOrderViewModel
import com.example.bip.presentation.ui.qrcode.scan.QrCodeScanViewModel
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

    fun getCreateOrderViewModel(): CreateOrderViewModel

    fun getSelectOrderViewModel(): SelectOrderViewModel

    fun getOfferHomeViewModel(): OfferHomeViewModel

    fun getQrCodeScanViewModel(): QrCodeScanViewModel

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): ApplicationComponent
    }

    fun context(): Context
}
