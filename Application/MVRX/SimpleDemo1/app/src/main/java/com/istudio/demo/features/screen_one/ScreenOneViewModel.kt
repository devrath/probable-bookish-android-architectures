package com.istudio.demo.features.screen_one

import com.airbnb.mvrx.MavericksViewModel
import javax.inject.Inject

class ScreenOneViewModel @Inject constructor(
    initialState : ScreenOneState
) : MavericksViewModel<ScreenOneState>(initialState){


}