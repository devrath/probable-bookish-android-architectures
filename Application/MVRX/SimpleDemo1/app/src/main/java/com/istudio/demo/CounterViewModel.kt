package com.istudio.demo

import com.airbnb.mvrx.MavericksViewModel

class CounterViewModel(initialState: CounterState) : MavericksViewModel<CounterState>(initialState) {


    fun incrementCount() {
        setState { copy(count = count + 1) }
    }



}