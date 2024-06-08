package com.istudio.demo.features.feature_counter

import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CounterScreenViewModel @Inject constructor(
    initialState: CounterScreenState
) : MavericksViewModel<CounterScreenState>(initialState) {

    init {
        viewModelObservers()
    }

    private fun viewModelObservers() {
        viewModelScope.launch {
            observerCompleteStateChanges()
            observeSinglePropertyChanges()
            observeMultiplePropertyChanges()
        }
    }

    fun incrementCounterOne() {
        setState { updateCounterOne() }
    }

    fun incrementCounterTwo() {
        setState { updateCounterTwo() }
    }

    /**
     * **************************************** Observers **************************************
     */
    private fun observerCompleteStateChanges() {
        onEach { state ->
            println("ScreenOneViewModel: State changed to $state")
        }
    }

    private fun observeSinglePropertyChanges() {
        onEach(CounterScreenState::counterOneInitialValue) { counterOneValue ->
            println("ScreenOneViewModel: counterOneInitialValue changed to $counterOneValue")
        }
    }

    private fun observeMultiplePropertyChanges() {
        onEach(
            CounterScreenState::counterOneInitialValue,
            CounterScreenState::counterTwoInitialValue
        ) { counterOneValue, counterTwoValue ->
            println("ScreenOneViewModel: counterOneInitialValue = $counterOneValue, counterTwoInitialValue = $counterTwoValue")
        }
    }
    /**
     * **************************************** Observers **************************************
     */
}