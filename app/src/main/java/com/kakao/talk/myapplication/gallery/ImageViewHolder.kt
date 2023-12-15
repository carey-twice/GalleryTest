package com.kakao.talk.myapplication.gallery

import android.net.Uri
import android.view.View
import com.bumptech.glide.Glide
import com.kakao.talk.myapplication.databinding.ItemImageViewBinding

class ImageViewHolder(itemView: View) : GalleryItemViewHolder(itemView) {

    private val binding = ItemImageViewBinding.bind(itemView)

    override fun bind(item: GalleryItem) {
        Glide.with(binding.photoView)
            .load(Uri.parse(item.uri))
            .centerCrop().into(binding.photoView)
    }
}