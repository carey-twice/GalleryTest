package com.kakao.talk.myapplication.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import com.kakao.talk.myapplication.databinding.ActivityGalleryBinding
import com.kakao.talk.myapplication.utils.dpToPx

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()

    private val adapter by lazy { GalleryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initViews()
        initViewModels()
    }

    private fun initViews() {
        binding.vpGallery.apply {
            adapter = this@GalleryActivity.adapter
            setPageTransformer(MarginPageTransformer(30f.dpToPx.toInt()))
        }
    }

    private fun initViewModels() {
        viewModel.init(contentResolver)
        viewModel.photoItems.observe(this) { items ->
            adapter.submitList(items)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, GalleryActivity::class.java)
        }
    }
}