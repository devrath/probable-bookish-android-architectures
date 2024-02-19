package com.istudio.catquote.api

import com.istudio.catquote.model.AnimalResponse
import retrofit2.http.GET


interface AnimalApi {
    @GET("/facts")
    suspend fun getAnimals(): AnimalResponse
}