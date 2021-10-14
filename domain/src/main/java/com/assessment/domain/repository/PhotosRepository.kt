package com.assessment.domain.repository

import com.assessment.domain.model.photo.PhotosItem
import io.reactivex.Single

interface PhotosRepository {
    fun getPhotos(albumId : String) : Single<List<PhotosItem>>
}