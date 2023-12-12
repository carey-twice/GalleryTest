package com.kakao.talk.myapplication.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionUtils {

    private val readMediaPermission: String
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        }

    fun checkReadMediaPermissions(activity: Activity, requestCode: Int = 0): Boolean {
        if (ActivityCompat.checkSelfPermission(
                activity,
                readMediaPermission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(readMediaPermission, android.Manifest.permission.ACCESS_MEDIA_LOCATION),
                requestCode
            )
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(readMediaPermission), requestCode)
        }
        return false
    }

    fun onReadMediaPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        action: () -> Unit = {},
    ) {
        onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            readMediaPermission,
            action
        )
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        requestPermission: String,
        action: () -> Unit,
    ) {
        permissions.indexOf(requestPermission).takeIf { it in grantResults.indices }?.let { index ->
            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                action()
            }
        }
    }
}