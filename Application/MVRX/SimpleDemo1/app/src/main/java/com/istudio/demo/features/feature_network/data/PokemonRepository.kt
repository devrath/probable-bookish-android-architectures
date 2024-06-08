package com.istudio.demo.features.feature_network.data

import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.ExperimentalMavericksApi
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksRepository
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.istudio.demo.features.feature_network.NetworkScreenState
import com.istudio.demo.features.feature_network.NetworkScreenUiState
import com.istudio.demo.features.feature_network.di.coroutines.dispatcher.IoDispatcher
import com.istudio.demo.features.feature_network.models.PokemonList
import com.istudio.demo.features.feature_network.models.Result
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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
        refresh() // Load initial data
    }

    fun refresh() {
        // Wrap the API call in a flow to handle loading and error states
        scope.launch {
            val result = api.getPokemonList()
            suspend {  }.execute { copy(pokemonList = result) }
        }
    }
}