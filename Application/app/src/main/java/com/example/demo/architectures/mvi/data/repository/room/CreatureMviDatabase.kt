package com.example.demo.architectures.mvi.data.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.demo.architectures.mvi.data.model.Creature

@Database(entities = [(Creature::class)], version = 1)
@TypeConverters(CreatureAttributesConverter::class)
abstract class CreatureMviDatabase : RoomDatabase() {
  abstract fun creatureDao(): CreatureMviDao
}