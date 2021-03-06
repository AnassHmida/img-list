package com.example.training.api

import com.example.training.models.picture
import retrofit2.http.GET
import retrofit2.http.Query

interface picturesAPI {

    companion object {

        const val BASE_URL = "https://mocki.io/v1/"
    }

    @GET("98f64983-1ab0-403b-b679-8ebff3275b30")
    suspend fun searchPhotos(): ArrayList<picture>
}