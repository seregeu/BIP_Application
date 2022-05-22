package com.example.bip.domain.usecase

import com.example.bip.domain.entity.PhotoData
import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface AddPhotoUseCase : (PhotoData, Int) -> Completable

class AddPhotoUseCaseImpl @Inject constructor(
    val orderRepository: OrderRepository
) : AddPhotoUseCase {

    override fun invoke(photoData: PhotoData, orderId: Int): Completable {
        return orderRepository.addPhoto(photoData = photoData, orderId = orderId)
    }
}
