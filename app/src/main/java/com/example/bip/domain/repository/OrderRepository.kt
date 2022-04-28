package com.example.bip.domain.repository

import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.entity.PhotoData
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author v.nasibullin
 */
interface OrderRepository {

    fun createOrder(createOrderData: CreateOrderData): Completable

    fun getBacklogOrders(): Single<List<OrderData>>

    fun selectOrder(orderData: OrderData): Completable

    fun addPhoto(photoData: PhotoData): Completable

    fun getPreview(): Single<String>

    fun finishOrder(): Single<String>
}
