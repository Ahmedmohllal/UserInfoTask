package com.assessment.data.repository

import com.assessment.data.mapper.mapToPhotosDomain
import com.assessment.data.remote.APIs
import com.assessment.domain.repository.PhotosRepository
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(private val remoteDataSource: APIs) :
    PhotosRepository {
    override fun getPhotos(albumId: String) = remoteDataSource.getAlbumPhotos(albumId).map {
        it.mapToPhotosDomain()
    }
}