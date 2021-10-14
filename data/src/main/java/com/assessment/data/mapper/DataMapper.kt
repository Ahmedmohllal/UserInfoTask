package com.assessment.data.mapper

import com.assessment.data.model.album.AlbumsInformation
import com.assessment.data.model.photo.PhotosItem
import com.assessment.domain.model.user.Address
import com.assessment.data.model.user.UserInformation
import com.assessment.domain.model.album.AlbumsInfo
import com.assessment.domain.model.user.UserInfo

fun List<UserInformation>.mapToUserInfoDomain() = this.map {
    UserInfo(
        address = Address(
            city = it.address.city,
            street = it.address.street,
            suite = it.address.suite,
            zipcode = it.address.zipcode
        ),
        id = it.id, name = it.name
    )
}

fun List<AlbumsInformation>.mapToAlbumsDomain() = this.map {
    AlbumsInfo(id = it.id, title = it.title)
}


fun List<PhotosItem>.mapToPhotosDomain() = this.map {
    com.assessment.domain.model.photo.PhotosItem(
        id = it.id,
        thumbnailUrl = it.thumbnailUrl,
        title = it.title
    )
}
