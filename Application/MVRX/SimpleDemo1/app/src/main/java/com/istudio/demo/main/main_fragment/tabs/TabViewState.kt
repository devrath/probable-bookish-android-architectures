package com.istudio.demo.main.main_fragment.tabs

import com.airbnb.mvrx.MavericksState

data class TabViewState(
    val tabTitle : String = "", val reselected : Boolean = false
) : MavericksState {
    fun screenWasReselected(screen : String) : Boolean {
        return tabTitle == screen && reselected
    }
}