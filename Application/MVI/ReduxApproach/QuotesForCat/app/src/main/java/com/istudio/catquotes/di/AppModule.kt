package com.istudio.catquotes.di

import com.istudio.catquotes.api.AnimalApi
import com.istudio.catquotes.api.AnimalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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