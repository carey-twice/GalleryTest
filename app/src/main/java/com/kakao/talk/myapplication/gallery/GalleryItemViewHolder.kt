package com.kakao.talk.myapplication.gallery

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class GalleryItemViewHolder(itemView: View) : ViewHolder(itemView) {

    open fun bind(item: GalleryItem) = Unit
}