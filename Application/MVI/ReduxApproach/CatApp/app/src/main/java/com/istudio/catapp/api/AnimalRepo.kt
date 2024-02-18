package com.istudio.catapp.api

import com.istudio.catapp.api.AnimalApi

class AnimalRepo(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getAnimals()
}