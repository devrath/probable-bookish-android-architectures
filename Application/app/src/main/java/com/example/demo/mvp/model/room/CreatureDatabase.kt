package com.example.demo.mvp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.demo.mvp.model.Creature

@Database(entities = [(Creature::class)], version = 1)
@TypeConverters(CreatureAttributesConverter::class)
abstract class CreatureDatabase : RoomDatabase() {
  abstract fun creatureDao(): CreatureDao
}