package com.istudio.catquote.api

import com.istudio.catquote.api.AnimalApi
import javax.inject.Inject

class AnimalRepo @Inject constructor(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getAnimals()
}