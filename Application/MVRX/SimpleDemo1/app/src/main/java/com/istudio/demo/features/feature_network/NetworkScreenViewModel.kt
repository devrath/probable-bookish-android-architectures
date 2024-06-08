package com.istudio.demo.features.feature_network

import android.util.Log
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.istudio.demo.MavericksApp
import com.istudio.demo.features.feature_network.data.PokemonRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkScreenViewModel @AssistedInject constructor(
    @Assisted initialState: NetworkScreenState,
    private val pokemonRepository: PokemonRepository
) : MavericksViewModel<NetworkScreenState>(initialState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<NetworkScreenViewModel, NetworkScreenState> {
        override fun create(state: NetworkScreenState): NetworkScreenViewModel
    }
    companion object : MavericksViewModelFactory<NetworkScreenViewModel, NetworkScreenState> by hiltMavericksViewModelFactory()



    init {
        getPokemonList()
    }

    private fun viewModelObservers() {
        // Observe data changes here if needed
    }

    private fun getPokemonList() {
        val pokemonList = pokemonRepository.refresh()
    }


}