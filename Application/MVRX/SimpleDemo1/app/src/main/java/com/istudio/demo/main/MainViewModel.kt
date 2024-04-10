package com.istudio.demo.main

import androidx.lifecycle.ViewModel
import com.airbnb.mvrx.MavericksViewModel
import com.istudio.demo.CounterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    initialState: MainViewState
) : MavericksViewModel<MainViewState>(initialState) {





}