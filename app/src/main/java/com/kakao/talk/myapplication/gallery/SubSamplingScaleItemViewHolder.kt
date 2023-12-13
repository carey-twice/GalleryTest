package com.kakao.talk.myapplication.gallery

import android.net.Uri
import android.util.Log
import android.view.View
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.kakao.talk.myapplication.databinding.ItemSubSamplingScaleBinding
import com.kakao.talk.myapplication.utils.ImageUtils

class SubSamplingScaleItemViewHolder(itemView: View) : GalleryItemViewHolder(itemView) {

    private val binding = ItemSubSamplingScaleBinding.bind(itemView)

    override fun bind(item: GalleryItem) {
        super.bind(item)
        Log.e("Main", "SubSamplingScaleItemViewHolder bind uri ${item.uri}")

        binding.photoView.apply {
            maxScale = 20f
            orientation = SubsamplingScaleImageView.ORIENTATION_USE_EXIF
            val imageSource = ImageSource.uri(item.uri)
            val format = ImageUtils.getImageFormat(
                context.contentResolver,
                Uri.parse(item.uri),
                ImageUtils.ImageFormat.UNKNOWN
            )
            if (format != ImageUtils.ImageFormat.PNG && format != ImageUtils.ImageFormat.JPEG) {
                imageSource.tilingDisabled()
            }

            setImage(imageSource)

            setOnImageEventListener(object : SubsamplingScaleImageView.DefaultOnImageEventListener() {
                override fun onReady() = Unit

                override fun onImageLoaded() = Unit

                override fun onPreviewLoadError(e: Exception?) = Unit

                override fun onImageLoadError(e: Exception?) = Unit

                override fun onTileLoadError(e: Exception?) = Unit
            })
        }
    }
}