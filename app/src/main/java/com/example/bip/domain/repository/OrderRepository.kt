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

    fun addPhoto(photoData: PhotoData, orderId: Int): Completable

    fun getPreview(idOrder: Int): Single<String>

    fun finishOrder(orderId: Int): Single<String>

    fun getAllClientOrders(): Single<List<OrderData>>

    fun getAllPhotographersOrders(): Single<List<OrderData>>
}
