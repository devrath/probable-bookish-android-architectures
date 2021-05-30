package com.example.demo.architectures.mvp.presenter

import androidx.lifecycle.LiveData
import com.example.demo.architectures.commonlayer.model.Creature
import com.example.demo.architectures.commonlayer.model.CreatureRepository
import com.example.demo.architectures.commonlayer.model.room.RoomRepository

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