package com.example.bip.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author v.nasibullin
 */
@Serializable
class AllOrdersResponse(
    @SerialName("active")
    val active: List<OrderDtoItem>? = null,

    @SerialName("backlog")
    val backlog: List<OrderDtoItem>? = null,

    @SerialName("finished")
    val finished: List<OrderDtoItem>? = null,
)
