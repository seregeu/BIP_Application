package com.example.bip.domain.usecase

import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
interface GetPreviewUseCase : (Int) -> (Single<String>)

class GetPreviewUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : GetPreviewUseCase {
    override fun invoke(id: Int): Single<String> {
        return orderRepository.getPreview(id)
    }
}
