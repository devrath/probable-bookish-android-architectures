package com.example.demo.architectures.commonlayer.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "creature_table")
data class Creature @JvmOverloads constructor(
        var attributes: CreatureAttributes = CreatureAttributes(),
        var hitPoints: Int = 0,
        @PrimaryKey @NonNull var name: String = "",
        var drawable: Int = 0
){

}