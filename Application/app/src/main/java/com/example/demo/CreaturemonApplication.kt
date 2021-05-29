package com.example.demo

import android.app.Application
import com.example.demo.mvp.model.room.CreatureDatabase

class CreaturemonApplication : Application() {

    companion object {
        lateinit var database: CreatureDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // TODO: init database
    }
}