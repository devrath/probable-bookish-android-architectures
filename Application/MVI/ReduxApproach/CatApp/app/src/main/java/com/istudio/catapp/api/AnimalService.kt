package com.istudio.catapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimalService {
    //API: https://alexwohlbruck.github.io/cat-facts/docs/
    const val BASE_URL = "https://cat-fact.herokuapp.com"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: AnimalApi = getRetrofit().create(AnimalApi::class.java)
}