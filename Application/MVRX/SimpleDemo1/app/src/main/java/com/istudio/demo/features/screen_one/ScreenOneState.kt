package com.istudio.demo.features.screen_one

import com.airbnb.mvrx.MavericksState

data class ScreenOneState(
    val state : ScreenOneUiState = ScreenOneUiState.Uninitilized
) : MavericksState

sealed class ScreenOneUiState {
    data object Uninitilized : ScreenOneUiState()
    data object Loading : ScreenOneUiState()
    data object Initilized : ScreenOneUiState()
}