package com.istudio.catapp.di

import com.istudio.catapp.api.AnimalApi
import com.istudio.catapp.api.AnimalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokeApi(): AnimalApi {
        return AnimalService.api
    }
}