package com.assessment.data.remote

import com.assessment.data.model.album.AlbumsInformation
import com.assessment.data.model.photo.PhotosItem
import com.assessment.data.model.user.UserInformation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("users")
    fun getUserInfo() : Single<List<UserInformation>>

    @GET("albums")
    fun getUserAlbums(@Query("userId") userId: String) : Single<List<AlbumsInformation>>

    @GET("photos")
    fun getAlbumPhotos(@Query("albumId") albumId: String) : Single<List<PhotosItem>>
}