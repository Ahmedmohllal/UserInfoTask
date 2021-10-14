package com.assessment.albumsassessment.core.wrapper

import androidx.lifecycle.MutableLiveData

class StateLiveData<T> : MutableLiveData<DataStatus<T>?>() {

    fun postLoading() {
        value = DataStatus<T>().loading()
    }

    fun postSuccess(data: T) {
        //value =
        postValue(DataStatus<T>().success(data))
    }


    fun postError(throwable: Throwable?) {
        value = DataStatus<T>().error(throwable)
    }


}