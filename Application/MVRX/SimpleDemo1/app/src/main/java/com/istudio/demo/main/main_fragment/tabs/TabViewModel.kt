package com.istudio.demo.main.main_fragment.tabs

import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val STATE_UPDATE_DELAY_MS = 250L
class TabViewModel(initialState : TabViewState) : MavericksViewModel<TabViewState>(initialState) {
    fun setTabReselected(title:String){
        viewModelScope.launch {
            setState { copy(tabTitle=title , reselected = true) }
            delay(STATE_UPDATE_DELAY_MS)
            setState { copy(tabTitle=title , reselected = false) }
        }
    }
}