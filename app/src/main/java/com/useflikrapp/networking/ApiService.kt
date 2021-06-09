package com.useflikrapp.networking

import com.useflikrapp.data.PhotosSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // UseFlikr App Name
    // Key:
    //6c146231a5bc5e41e1a5955a6b2025eb
    //
    //Secret:
    //235a973ea66e4c58
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=dogs&api_key=6c146231a5bc5e41e1a5955a6b2025eb")
    suspend fun getPhotos(): PhotosSearchResponse

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=6c146231a5bc5e41e1a5955a6b2025eb")
    suspend fun getPhotos(@Query(value = "text") searchTerm: String): PhotosSearchResponse
}
