package com.example.demo.mvp.presenter

import androidx.lifecycle.LiveData
import com.example.demo.mvp.model.Creature


interface AllCreaturesContract {

  interface Presenter {
    fun getAllCreatures(): LiveData<List<Creature>>
    fun clearAllCreatures()
  }

  interface View {
    fun showCreaturesCleared()
  }
}