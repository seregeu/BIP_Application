package com.example.bip.di.modules

import com.example.bip.data.repositoy.*
import com.example.bip.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface RepositoryModule {

    @Reusable
    @Binds
    fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Reusable
    @Binds
    fun bindRegisterRepository(registerRepository: RegistrationRepositoryImpl): RegistrationRepository

    @Reusable
    @Binds
    fun bindProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository

    @Reusable
    @Binds
    fun bindOrderRepository(orderRepository: OrderRepositoryImpl): OrderRepository

    @Reusable
    @Binds
    fun bindOfferRepository(offerRepository: OfferRepositoryImpl): OfferRepository

    @Reusable
    @Binds
    fun bindQrCodeRepository(qrCodeRepositoryImpl: QrCodeRepositoryImpl): QrCodeRepository
}
