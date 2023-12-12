package com.kakao.talk.myapplication.ui

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.ViewTreeObserver
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

class PhotoSubsamplingScaleImageView(context: Context?, attr: AttributeSet?) :
    SubsamplingScaleImageView(context, attr) {

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                resetScaleAndCenter()
            }
        })
    }
}