package com.kakao.talk.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.talk.myapplication.databinding.ActivityMainBinding
import com.kakao.talk.myapplication.gallery.GalleryActivity
import com.kakao.talk.myapplication.gallery.GalleryAdapter
import com.kakao.talk.myapplication.utils.PermissionUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var type: Int = GalleryAdapter.TYPE_SUB_SAMPLING_SCALE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnView1.setOnClickListener {
            startGalleryActivity(GalleryAdapter.TYPE_SUB_SAMPLING_SCALE)
        }
        binding.btnView2.setOnClickListener {
            startGalleryActivity(GalleryAdapter.TYPE_PHOTO_VIEW)
        }
        binding.btnView3.setOnClickListener {
            startGalleryActivity(GalleryAdapter.TYPE_IMAGE_VIEW)
        }
    }

    private fun startGalleryActivity(type: Int) {
        this.type = type

        if (PermissionUtils.checkReadMediaPermissions(this)) {
            startActivity(GalleryActivity.newIntent(this, type))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        PermissionUtils.onReadMediaPermissionsResult(requestCode, permissions, grantResults) {
            startActivity(GalleryActivity.newIntent(this@MainActivity, type))
        }
    }
}