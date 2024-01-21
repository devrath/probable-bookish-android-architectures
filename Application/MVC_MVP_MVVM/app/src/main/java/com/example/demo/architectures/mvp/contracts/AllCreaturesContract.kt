package com.example.demo.architectures.mvp.contracts

import androidx.lifecycle.LiveData
import com.example.demo.architectures.commonlayer.model.Creature


interface AllCreaturesContract {

  interface Presenter {
    fun getAllCreatures(): LiveData<List<Creature>>
    fun clearAllCreatures()
  }

  interface View {
    fun showCreaturesCleared()
  }
}