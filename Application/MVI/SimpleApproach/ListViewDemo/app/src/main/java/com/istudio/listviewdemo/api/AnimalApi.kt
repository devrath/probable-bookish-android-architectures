package com.istudio.listviewdemo.api

import com.istudio.listviewdemo.model.Animal
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>

}