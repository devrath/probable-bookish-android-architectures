package com.istudio.demo.features.feature_counter

import com.airbnb.mvrx.MavericksState

data class CounterScreenState(
    val state : CounterScreenUiState = CounterScreenUiState.Uninitialized,
    val counterOneInitialValue : Int = 0,
    val counterTwoInitialValue : Int = 0
) : MavericksState {
    fun updateCounterOne() = copy(counterOneInitialValue = counterOneInitialValue + 1)
    fun updateCounterTwo() = copy(counterTwoInitialValue = counterTwoInitialValue + 1)
}

sealed class CounterScreenUiState {
    data object Uninitialized : CounterScreenUiState()
    data object Loading : CounterScreenUiState()
    data object Initilized : CounterScreenUiState()
}