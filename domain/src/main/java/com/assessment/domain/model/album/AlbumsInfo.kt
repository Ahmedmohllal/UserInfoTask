package com.assessment.domain.model.album


import com.google.gson.annotations.SerializedName

data class AlbumsInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)