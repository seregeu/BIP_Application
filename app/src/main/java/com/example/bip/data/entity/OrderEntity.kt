package com.example.bip.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author v.nasibullin
 */
@Entity(tableName = "order", indices = [Index(value = ["id"], unique = true)])
data class OrderEntity(
    @ColumnInfo(name = "client_id")
    val clientId: Int,

    @ColumnInfo(name = "comment")
    val comment: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "order_cost")
    val orderCost: Int,

    @ColumnInfo(name = "order_state")
    val orderState: String,

    @ColumnInfo(name = "photographer_id")
    val photographerId: Int
)
