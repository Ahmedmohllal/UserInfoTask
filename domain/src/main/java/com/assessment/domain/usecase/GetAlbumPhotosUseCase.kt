package com.assessment.domain.usecase

import com.assessment.domain.repository.PhotosRepository
import javax.inject.Inject

class GetAlbumPhotosUseCase @Inject constructor(private val photosRepo : PhotosRepository) {

    fun invoke(albumsId : String) = photosRepo.getPhotos(albumsId)
}