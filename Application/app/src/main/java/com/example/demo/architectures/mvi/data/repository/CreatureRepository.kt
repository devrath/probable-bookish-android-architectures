package com.example.demo.architectures.mvi.data.repository

import com.example.demo.architectures.mvi.data.model.Creature
import io.reactivex.Observable

interface CreatureRepository {
  fun saveCreature(creature: Creature): Observable<Boolean>
  fun getAllCreatures(): Observable<List<Creature>>
  fun clearAllCreatures(): Observable<Boolean>
}