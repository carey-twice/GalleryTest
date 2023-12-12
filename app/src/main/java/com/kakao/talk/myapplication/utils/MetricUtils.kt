package com.kakao.talk.myapplication.utils

import android.content.res.Resources

inline val Number.dpToPx: Float
    get() = toFloat() * Resources.getSystem().displayMetrics.density