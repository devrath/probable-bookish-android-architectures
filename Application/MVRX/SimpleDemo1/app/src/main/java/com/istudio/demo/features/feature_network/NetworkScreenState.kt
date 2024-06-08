package com.istudio.demo.features.feature_network

import com.airbnb.mvrx.MavericksState
import com.istudio.demo.features.feature_network.models.PokemonList
import com.istudio.demo.features.feature_network.models.Result

data class NetworkScreenState(
    val state: NetworkScreenUiState = NetworkScreenUiState.Uninitialized,
    val pokemonList: PokemonList? = null,
    val errorMessage: String? = null
) : MavericksState {
}

sealed class NetworkScreenUiState {
    data object Uninitialized : NetworkScreenUiState()
    data object Loading : NetworkScreenUiState()
    data object Initilized : NetworkScreenUiState()
    data object Success : NetworkScreenUiState()
    data class Error(val message: String) : NetworkScreenUiState()
}