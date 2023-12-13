package com.kakao.talk.myapplication.gallery

import android.net.Uri
import android.util.Log
import android.view.View
import com.kakao.talk.myapplication.databinding.ItemPhotoViewBinding

class PhotoItemViewHolder(itemView: View) : GalleryItemViewHolder(itemView) {

    private val binding = ItemPhotoViewBinding.bind(itemView)

    override fun bind(item: GalleryItem) {
        super.bind(item)
        Log.e("Main", "PhotoItemViewHolder bind uri ${item.uri}")

        binding.photoView.setImageURI(Uri.parse(item.uri))
    }
}