package com.istudio.catquotes.api

import com.istudio.catquotes.model.AnimalResponse
import retrofit2.http.GET


interface AnimalApi {
    @GET("/facts")
    suspend fun getAnimals(): AnimalResponse
}