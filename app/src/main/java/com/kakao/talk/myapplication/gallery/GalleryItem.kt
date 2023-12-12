package com.kakao.talk.myapplication.gallery

data class GalleryItem(
    val id: Long,
    val uri: String,
    val dateModified: Long,
    val size: Long,
    val mimeType: String,
)