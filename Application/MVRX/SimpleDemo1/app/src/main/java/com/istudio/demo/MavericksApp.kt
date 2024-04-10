package com.istudio.demo

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MavericksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initMavericks()
    }

    private fun initMavericks() {
        Mavericks.initialize(
            this,
            viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory()
        )
    }


}