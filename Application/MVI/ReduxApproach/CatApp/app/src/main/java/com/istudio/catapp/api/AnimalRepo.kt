package com.istudio.catapp.api

import com.istudio.catapp.api.AnimalApi
import javax.inject.Inject

class AnimalRepo @Inject constructor(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getAnimals()
}