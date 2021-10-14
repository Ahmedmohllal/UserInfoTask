package com.assessment.domain.repository

import com.assessment.domain.model.album.AlbumsInfo
import io.reactivex.Single

interface AlbumsRepository {
    fun getUserAlbums(userId : String) : Single<List<AlbumsInfo>>
}