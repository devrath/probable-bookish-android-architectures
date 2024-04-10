package com.istudio.demo.main

import android.os.Parcelable
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import kotlinx.parcelize.Parcelize

data class MainViewState(
    @PersistState val state : MainViewUiState = MainViewUiState.Splash
) : MavericksState


sealed class MainViewUiState : Parcelable {
    @Parcelize
    data object Uninitialized : MainViewUiState()
    @Parcelize
    data object Splash : MainViewUiState()
    @Parcelize
    data object EnterMainPage : MainViewUiState()
}


