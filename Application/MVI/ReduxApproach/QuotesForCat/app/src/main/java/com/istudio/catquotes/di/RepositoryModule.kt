package com.istudio.catquotes.di

import com.istudio.catquotes.api.AnimalApi
import com.istudio.catquotes.api.AnimalRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun getRepository(api: AnimalApi): AnimalRepo {
        return AnimalRepo(api)
    }

}