package com.istudio.listviewdemo.api

import com.istudio.listviewdemo.api.AnimalApi

class AnimalRepo(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getAnimals()
}