package com.example.bip.di.modules

import com.example.bip.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface UseCaseModule {

    @Reusable
    @Binds
    fun bindRegistration(registerUseCase: RegisterUseCaseImpl): RegisterUseCase

    @Reusable
    @Binds
    fun bindAuthorization(authUseCase: AuthUseCaseImpl): AuthUseCase

    @Reusable
    @Binds
    fun bindGetProfileUseCase(getProfileUseCase: GetProfileUseCaseImpl): GetProfileUseCase

    @Reusable
    @Binds
    fun bindAuth2Fa(auth2FaUseCase: Auth2FaUseCaseImpl): Auth2FaUseCase

    @Reusable
    @Binds
    fun bindCreateOrderUseCase(createOrderUseCase: CreateOrderUseCaseImpl): CreateOrderUseCase

    @Reusable
    @Binds
    fun bindGetBacklogUseCase(getBacklogOrders: GetBacklogOrdersImpl): GetBacklogOrders

    @Reusable
    @Binds
    fun bindSelectUseCase(selectOrderUseCaseImpl: SelectOrderUseCaseImpl): SelectOrderUseCase

    @Reusable
    @Binds
    fun bindGetOfferUseCase(getOffersUseCaseImpl: GetOffersUseCaseImpl): GetOffersUseCase

    @Reusable
    @Binds
    fun bindSelectPhotographerUseCase(selectPhotographerUseCase: SelectPhotographerUseCaseImpl): SelectPhotographerUseCase

    @Reusable
    @Binds
    fun bindConfirmQrCodeUseCase(confirmQrCodeUseCaseImpl: ConfirmQrCodeUseCaseImpl): ConfirmQrCodeUseCase

    @Reusable
    @Binds
    fun bindGenerateQrCodeUseCase(generateQrCodeUseCase: GenerateQrCodeUseCaseImpl): GenerateQrCodeUseCase

    @Reusable
    @Binds
    fun bindAddPhotoUseCase(addPhotoUseCase: AddPhotoUseCaseImpl): AddPhotoUseCase

    @Reusable
    @Binds
    fun bindFinishOrderUseCase(finishOrderUseCase: FinishOrderUseCaseImpl): FinishOrderUseCase

    @Reusable
    @Binds
    fun bindGetPreviewUseCase(getPreviewUseCase: GetPreviewUseCaseImpl): GetPreviewUseCase

    @Reusable
    @Binds
    fun bindGetOrderWithType(getOrdersWithTypeImpl: GetOrdersWithTypeImpl): GetOrdersWithType

    @Reusable
    @Binds
    fun bindGetPhotographerOrder(getPhotographerOrderImpl: GetPhotographerOrderImpl): GetPhotographerOrder
}
