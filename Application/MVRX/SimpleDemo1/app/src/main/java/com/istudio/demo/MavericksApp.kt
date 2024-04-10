package com.istudio.demo

import android.app.Application
import com.airbnb.mvrx.Mavericks

class MavericksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }


}