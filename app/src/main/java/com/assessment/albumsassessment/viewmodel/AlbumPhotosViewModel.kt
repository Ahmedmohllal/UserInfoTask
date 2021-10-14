package com.assessment.albumsassessment.viewmodel

import com.assessment.albumsassessment.core.BaseViewModel
import com.assessment.albumsassessment.core.wrapper.StateLiveData
import com.assessment.domain.model.photo.PhotosItem
import com.assessment.domain.usecase.GetAlbumPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
open class AlbumPhotosViewModel @Inject constructor(
    private val photosInfo: GetAlbumPhotosUseCase
) : BaseViewModel() {

    val photosInfoLiveData by lazy { StateLiveData<List<PhotosItem>>() }

    fun getPhotos(albumId: String) {
        compositeDisposable.add(photosInfo.invoke(albumId)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                photosInfoLiveData.postLoading()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    photosInfoLiveData.postSuccess(response)
                }, { error ->
                    photosInfoLiveData.postError(error)

                })
        )
    }

}