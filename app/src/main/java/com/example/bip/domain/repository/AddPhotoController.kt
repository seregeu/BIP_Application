package com.example.bip.domain.repository

import com.example.bip.domain.entity.PhotoData

/**
 * @author v.nasibullin
 */
interface AddPhotoController {

    fun addPhoto(photoData: PhotoData)
}
