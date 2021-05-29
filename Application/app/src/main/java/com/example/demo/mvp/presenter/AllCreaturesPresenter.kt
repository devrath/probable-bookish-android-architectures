package com.example.demo.mvp.presenter

import androidx.lifecycle.LiveData
import com.example.demo.mvp.model.Creature
import com.example.demo.mvp.model.CreatureRepository
import com.example.demo.mvp.model.room.RoomRepository

class AllCreaturesPresenter(private val repository: CreatureRepository = RoomRepository())
  : BasePresenter<AllCreaturesContract.View>(), AllCreaturesContract.Presenter {

  override fun getAllCreatures(): LiveData<List<Creature>> {
    return repository.getAllCreatures()
  }

  override fun clearAllCreatures() {
    repository.clearAllCreatures()
    getView()?.showCreaturesCleared()
  }
}