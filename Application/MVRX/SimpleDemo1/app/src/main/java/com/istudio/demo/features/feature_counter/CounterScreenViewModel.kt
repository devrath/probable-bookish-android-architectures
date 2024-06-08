package com.istudio.demo.features.feature_counter

import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CounterScreenViewModel @Inject constructor(
    initialState: ScreenOneState
) : MavericksViewModel<ScreenOneState>(initialState) {

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
        onEach(ScreenOneState::counterOneInitialValue) { counterOneValue ->
            println("ScreenOneViewModel: counterOneInitialValue changed to $counterOneValue")
        }
    }

    private fun observeMultiplePropertyChanges() {
        onEach(
            ScreenOneState::counterOneInitialValue,
            ScreenOneState::counterTwoInitialValue
        ) { counterOneValue, counterTwoValue ->
            println("ScreenOneViewModel: counterOneInitialValue = $counterOneValue, counterTwoInitialValue = $counterTwoValue")
        }
    }
    /**
     * **************************************** Observers **************************************
     */
}