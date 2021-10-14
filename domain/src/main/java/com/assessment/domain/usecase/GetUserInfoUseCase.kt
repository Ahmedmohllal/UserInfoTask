package com.assessment.domain.usecase

import com.assessment.domain.model.user.UserInfo
import com.assessment.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val userRepo : UserRepository) {

    fun invoke() = userRepo.getUserInfo()
}