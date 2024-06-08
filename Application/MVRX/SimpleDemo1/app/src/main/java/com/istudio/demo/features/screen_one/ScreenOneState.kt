package com.istudio.demo.features.screen_one

import com.airbnb.mvrx.MavericksState

data class ScreenOneState(
    val state : ScreenOneUiState = ScreenOneUiState.Uninitialized,
    val counterOneInitialValue : Int = 0,
    val counterTwoInitialValue : Int = 0
) : MavericksState {
    fun updateCounterOne() = copy(counterOneInitialValue = counterOneInitialValue + 1)
    fun updateCounterTwo() = copy(counterTwoInitialValue = counterTwoInitialValue + 1)
}

sealed class ScreenOneUiState {
    data object Uninitialized : ScreenOneUiState()
    data object Loading : ScreenOneUiState()
    data object Initilized : ScreenOneUiState()
}