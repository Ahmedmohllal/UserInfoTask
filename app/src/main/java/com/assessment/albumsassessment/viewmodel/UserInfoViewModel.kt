package com.assessment.albumsassessment.viewmodel

import android.util.Log
import com.assessment.albumsassessment.core.BaseViewModel
import com.assessment.albumsassessment.core.wrapper.StateLiveData
import com.assessment.domain.model.album.AlbumsInfo
import com.assessment.domain.model.user.UserInfo
import com.assessment.domain.usecase.GetUserAlbumsUseCase
import com.assessment.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
open class UserInfoViewModel @Inject constructor(
    private val userInfo: GetUserInfoUseCase,
    private val userAlbums: GetUserAlbumsUseCase
) :
    BaseViewModel() {

    val userInfoLiveData by lazy { StateLiveData<List<UserInfo>>() }
    val userAlbumsLiveData by lazy { StateLiveData<List<AlbumsInfo>>() }

    fun getUserInfo() {
        compositeDisposable.add(userInfo.invoke()
            .flatMap {
                userInfoLiveData.postSuccess(it)
                userAlbums.invoke(it[0].id.toString())
            }
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                userInfoLiveData.postLoading()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    userAlbumsLiveData.postSuccess(response)
                }, { error ->
                    userInfoLiveData.postError(error)
                })

        )
    }


}