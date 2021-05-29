package com.example.demo.mvp.model.room

import androidx.room.RoomDatabase

abstract class CreatureDatabase : RoomDatabase() {
  abstract fun creatureDao(): CreatureDao
}