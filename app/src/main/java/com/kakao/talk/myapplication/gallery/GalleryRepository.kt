package com.kakao.talk.myapplication.gallery

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GalleryRepository {

    suspend fun loadPhotos(contentResolver: ContentResolver) = withContext(Dispatchers.IO) {
        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection(),
            null,
            null,
            "${MediaStore.Images.Media.DATE_MODIFIED} DESC"
        )?.use {
            val items = mutableListOf<GalleryItem>()

            val idIndex = it.getColumnIndex(MediaStore.Images.Media._ID)
            val dateModifiedIndex = it.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED)
            val sizeIndex = it.getColumnIndex(MediaStore.Images.Media.SIZE)
            val mimeTypeIndex = it.getColumnIndex(MediaStore.Images.Media.MIME_TYPE)

            while (it.moveToNext()) {
                val id = it.getLong(idIndex)
                val item = GalleryItem(
                    id = id,
                    uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString()).toString(),
                    dateModified = it.getLong(dateModifiedIndex),
                    size = it.getLong(sizeIndex),
                    mimeType = it.getString(mimeTypeIndex),
                    )

                items.add(item)
            }

            items
        } ?: emptyList()
    }

    private fun projection(): Array<String> {
        return mutableListOf<String>().apply {
            add(MediaStore.Images.Media._ID)
            add(MediaStore.Images.Media.DATE_MODIFIED)
            add(MediaStore.Images.Media.SIZE)
            add(MediaStore.Images.Media.MIME_TYPE)
        }.toTypedArray()
    }
}