package com.example.bip.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bip.data.entity.OrderEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author v.nasibullin
 */
@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderEntity: OrderEntity): Completable

    @Query("SELECT id FROM `order`")
    fun getOrderId(): Single<Int>
}
