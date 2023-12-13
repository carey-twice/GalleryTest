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

    private val adapter by lazy { GalleryAdapter(type = type) }
    private val type: Int
        get() = intent.getIntExtra(EXTRA_TYPE, GalleryAdapter.TYPE_SUB_SAMPLING_SCALE)

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
        private const val EXTRA_TYPE = "type"

        fun newIntent(context: Context, type: Int): Intent {
            return Intent(context, GalleryActivity::class.java)
                .putExtra(EXTRA_TYPE, type)
        }
    }
}