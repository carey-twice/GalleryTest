package com.kakao.talk.myapplication.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kakao.talk.myapplication.R

class GalleryAdapter : ListAdapter<GalleryItem, GalleryItemViewHolder>(GalleryItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        return GalleryItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gallery, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GalleryItemDiffCallback : DiffUtil.ItemCallback<GalleryItem>() {
        override fun areItemsTheSame(
            oldItem: GalleryItem, newItem: GalleryItem,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: GalleryItem, newItem: GalleryItem,
        ): Boolean = oldItem.id == newItem.id
                && oldItem.uri == newItem.uri
                && oldItem.dateModified == newItem.dateModified
                && oldItem.size == newItem.size
                && oldItem.mimeType == newItem.mimeType
    }
}