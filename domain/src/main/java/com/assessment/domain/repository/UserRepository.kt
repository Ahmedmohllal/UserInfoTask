package com.assessment.domain.repository

import com.assessment.domain.model.user.UserInfo
import io.reactivex.Single

interface UserRepository {

    fun getUserInfo() : Single<List<UserInfo>>
}