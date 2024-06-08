package com.istudio.demo.features.feature_network.data

import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.ExperimentalMavericksApi
import com.airbnb.mvrx.MavericksRepository
import com.istudio.demo.features.feature_network.NetworkScreenState
import com.istudio.demo.features.feature_network.NetworkScreenUiState
import com.istudio.demo.features.feature_network.di.coroutines.dispatcher.IoDispatcher
import com.istudio.demo.features.feature_network.models.PokemonList
import com.istudio.demo.features.feature_network.models.Result
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalMavericksApi::class)
class PokemonRepository @Inject constructor(
    private val api: PokeApi,
    private val scope: CoroutineScope
) : MavericksRepository<NetworkScreenState>(
    initialState = NetworkScreenState(),
    coroutineScope = scope,
    performCorrectnessValidations = BuildConfig.DEBUG
) {
    init {
        suspend { api.getPokemonList() }.execute { copy(pokemonList = it.invoke()) }
    }

    fun refresh() {
        suspend { api.getPokemonList() }.execute { copy(pokemonList = it.invoke()) }
    }
}