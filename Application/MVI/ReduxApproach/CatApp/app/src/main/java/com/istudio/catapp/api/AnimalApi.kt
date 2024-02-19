package com.istudio.catapp.api

import com.istudio.catapp.model.AnimalResponse
import retrofit2.http.GET


interface AnimalApi {
    @GET("/facts")
    suspend fun getAnimals(): AnimalResponse
}