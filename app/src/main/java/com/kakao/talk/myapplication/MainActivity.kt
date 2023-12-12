package com.kakao.talk.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.talk.myapplication.databinding.ActivityMainBinding
import com.kakao.talk.myapplication.gallery.GalleryActivity
import com.kakao.talk.myapplication.utils.PermissionUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnView.setOnClickListener {
            if (PermissionUtils.checkReadMediaPermissions(this)) {
                startActivity(GalleryActivity.newIntent(this@MainActivity))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        PermissionUtils.onReadMediaPermissionsResult(requestCode, permissions, grantResults) {
            startActivity(GalleryActivity.newIntent(this@MainActivity))
        }
    }
}