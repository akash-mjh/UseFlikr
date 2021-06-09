package com.useflikrapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.useflikrapp.domain.Photo
import com.useflikrapp.networking.WebClient
import kotlinx.coroutines.launch


class ShowPhotosViewModel  : ViewModel()  {

    private val mPhotosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = mPhotosLiveData

    init {
        viewModelScope.launch {
            val searchResponse = WebClient.client.getPhotos()
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mPhotosLiveData.postValue(photosList)
        }
    }


    fun fetchImages(searchTerm: String) {
        if (searchTerm.isBlank()) {
            mPhotosLiveData.postValue(emptyList())
            return
        }
        viewModelScope.launch {
            val searchResponse = WebClient.client.getPhotos(searchTerm)
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mPhotosLiveData.postValue(photosList)
        }
    }


}