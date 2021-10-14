package com.assessment.data.repository

import com.assessment.data.mapper.mapToAlbumsDomain
import com.assessment.data.remote.APIs
import com.assessment.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(private val remoteDataSource: APIs) :
    AlbumsRepository {
    override fun getUserAlbums(userId: String) = remoteDataSource.getUserAlbums(userId).map {
        it.mapToAlbumsDomain()
    }

}