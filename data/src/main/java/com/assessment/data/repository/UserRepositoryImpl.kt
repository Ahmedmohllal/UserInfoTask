package com.assessment.data.repository

import com.assessment.data.mapper.mapToUserInfoDomain
import com.assessment.data.remote.APIs
import com.assessment.domain.model.user.UserInfo
import com.assessment.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val remoteDataSource: APIs) : UserRepository {

    override fun getUserInfo() = remoteDataSource.getUserInfo().map {
        it.mapToUserInfoDomain()
    }
}