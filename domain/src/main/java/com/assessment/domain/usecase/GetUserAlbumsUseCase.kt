package com.assessment.domain.usecase

import com.assessment.domain.repository.AlbumsRepository
import javax.inject.Inject

class GetUserAlbumsUseCase @Inject constructor(private val albumsRepo : AlbumsRepository) {

    fun invoke(userId : String) = albumsRepo.getUserAlbums(userId)
}