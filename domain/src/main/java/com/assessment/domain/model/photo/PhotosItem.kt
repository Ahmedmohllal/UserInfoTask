package com.assessment.domain.model.photo


import com.google.gson.annotations.SerializedName

data class PhotosItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String
)