package com.example.bip.data.repositoy

import com.example.bip.data.db.dao.OrderDao
import com.example.bip.data.entity.PhotoBody
import com.example.bip.data.mapper.OrderDataToBody
import com.example.bip.data.mapper.OrderDtoToEntity
import com.example.bip.data.mapper.OrderResponseToData
import com.example.bip.data.network.ApiService
import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.domain.entity.OrderData
import com.example.bip.domain.entity.PhotoData
import com.example.bip.domain.repository.OrderRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author v.nasibullin
 */
class OrderRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val orderDataToBody: OrderDataToBody,
    private val orderResponseToData: OrderResponseToData,
    private val orderDao: OrderDao,
    private val orderDtoToEntity: OrderDtoToEntity,
) : OrderRepository {
    override fun createOrder(createOrderData: CreateOrderData): Completable {
        return apiService.createOrder(orderDataToBody(createOrderData))
            .flatMapCompletable {
                orderDao.insert(orderDtoToEntity(it))
            }
    }

    override fun getBacklogOrders(): Single<List<OrderData>> {
        return apiService.getOrders()
            .map { it.orderResponse.map(orderResponseToData) }
    }

    override fun selectOrder(orderData: OrderData): Completable {
        return apiService.selectOrder(orderData.id)
    }

    override fun addPhoto(photoData: PhotoData): Completable {
        return apiService.getAllOrdersPhoto()
            .flatMapCompletable { orderList ->
                apiService.uploadPhoto(
                    idOrder = orderList.active?.firstOrNull()?.id ?: -1,
                    photoBody = PhotoBody(photoData.urlOrigin, photoData.waterMark)
                )
            }
    }
}
