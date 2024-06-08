package com.istudio.demo.features.screen_one

import com.airbnb.mvrx.MavericksViewModel
import javax.inject.Inject

class ScreenOneViewModel @Inject constructor(
    initialState : ScreenOneState
) : MavericksViewModel<ScreenOneState>(initialState){

    init {
        viewModelObservers()
    }

    private fun viewModelObservers() {
        observerCompleteStateChanges()
        observeSinglePropertyChanges()
        observeMultiplePropertyChanges()
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
    /**
     * Invoked whenever a state changes.
     */
    private fun observerCompleteStateChanges() {
        onEach { state ->
            println("ScreenOneViewModel: $state")
        }
    }

    /**
     * Invoked whenever one property changes.
     */
    private fun observeSinglePropertyChanges() {
        onEach(ScreenOneState::counterOneInitialValue) { screenOneState ->
            println("ScreenOneViewModel: $screenOneState")
        }
    }

    /**
     * Invoked whenever more than one properties changes.
     */
    private fun observeMultiplePropertyChanges() {
        onEach(
            ScreenOneState::counterOneInitialValue,
            ScreenOneState::counterTwoInitialValue
        ) { screenOneState , screenTwoState ->
            println("ScreenOneViewModel: $screenOneState, $screenTwoState")
        }
    }
    /**
     * **************************************** Observers **************************************
     */


}