package com.assessment.data.model.album


import com.google.gson.annotations.SerializedName

data class AlbumsInformation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)