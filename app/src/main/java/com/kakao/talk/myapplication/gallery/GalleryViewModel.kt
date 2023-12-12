package com.kakao.talk.myapplication.gallery

import android.content.ContentResolver
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {

    private val _photoItems: MutableLiveData<List<GalleryItem>> = MutableLiveData()
    val photoItems: LiveData<List<GalleryItem>> = _photoItems

    fun init(contentResolver: ContentResolver) {
        viewModelScope.launch {
            Log.e("Main", "loadPhotos start...")
            _photoItems.value = GalleryRepository.loadPhotos(contentResolver).also {
                Log.e("Main", "loadPhotos end... size ${it.size}")
            }
        }
    }
}