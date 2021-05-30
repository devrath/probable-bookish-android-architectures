package com.example.demo.architectures.mvi.data.repository.room

import androidx.room.*
import com.example.demo.architectures.mvi.data.model.Creature
import io.reactivex.Observable

@Dao
interface CreatureMviDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(creature: Creature)

  @Query("DELETE FROM creature_table")
  fun clearAllCreatures()

  @Query("SELECT * FROM creature_table ORDER BY name ASC")
  fun getAllCreatures(): Observable<List<Creature>>
}