package com.example.bip.presentation.ui.offers.client

import java.io.Serializable

/**
 * @author v.nasibullin
 */
data class People(
    val id: Int,
    val genre: String = "Pop",
    val artist: String,
    val song: String,
    val descriptions: String,
    val imageId: Int,
    val swiped: Boolean = false
) : Serializable
