package com.example.demo

import android.app.Application
import androidx.room.Room
import com.example.demo.architectures.commonlayer.model.room.CreatureDatabase
import com.example.demo.architectures.mvi.data.repository.room.CreatureMviDatabase

class CreaturemonApplication : Application() {

    companion object {
        lateinit var database: CreatureDatabase
        lateinit var databaseMvi: CreatureMviDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, CreatureDatabase::class.java, "creature_database")
            .build()
        databaseMvi = Room.databaseBuilder(this, CreatureMviDatabase::class.java, "creature_database")
            .build()
    }
}