package com.assessment.domain.model.user


import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("address")
    val address: Address,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)