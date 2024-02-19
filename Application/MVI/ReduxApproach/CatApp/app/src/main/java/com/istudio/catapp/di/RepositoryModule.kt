package com.istudio.catapp.di

import com.istudio.catapp.api.AnimalApi
import com.istudio.catapp.api.AnimalRepo
import dagger.Binds
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