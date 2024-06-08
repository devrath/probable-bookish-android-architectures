package com.istudio.demo.features.feature_network

import com.airbnb.mvrx.MavericksState

data class NetworkScreenState(
    val state : NetworkScreenUiState = NetworkScreenUiState.Uninitialized
) : MavericksState {
}

sealed class NetworkScreenUiState {
    data object Uninitialized : NetworkScreenUiState()
    data object Loading : NetworkScreenUiState()
    data object Initilized : NetworkScreenUiState()
}