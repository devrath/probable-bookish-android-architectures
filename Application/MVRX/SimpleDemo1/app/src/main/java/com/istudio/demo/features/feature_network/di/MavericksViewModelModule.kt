package com.istudio.demo.features.feature_network.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.istudio.demo.features.feature_network.NetworkScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface MavericksViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NetworkScreenViewModel::class)
    fun networkScreenViewModelFactory(factory: NetworkScreenViewModel.Factory): AssistedViewModelFactory<*, *>
}