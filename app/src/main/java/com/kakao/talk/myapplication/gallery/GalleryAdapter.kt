package com.kakao.talk.myapplication.gallery

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kakao.talk.myapplication.R

class GalleryAdapter(private val type: Int = TYPE_SUB_SAMPLING_SCALE) :
    ListAdapter<GalleryItem, GalleryItemViewHolder>(GalleryItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        return when (type) {
            TYPE_SUB_SAMPLING_SCALE -> {
                Log.e("Main", "SubSamplingScaleItemViewHolder create...")
                SubSamplingScaleItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_sub_sampling_scale, parent, false)
                )
            }

            TYPE_PHOTO_VIEW -> {
                Log.e("Main", "PhotoItemViewHolder create...")
                PhotoItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_photo_view, parent, false)
                )
            }

            else -> {
                throw IllegalArgumentException("Invalid type")
            }
        }
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

    companion object {
        const val TYPE_SUB_SAMPLING_SCALE = 0
        const val TYPE_PHOTO_VIEW = 1
    }
}