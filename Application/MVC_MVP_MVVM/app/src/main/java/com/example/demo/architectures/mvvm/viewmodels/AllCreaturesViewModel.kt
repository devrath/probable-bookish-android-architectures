package com.example.demo.architectures.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.demo.architectures.commonlayer.model.CreatureRepository
import com.example.demo.architectures.commonlayer.model.room.RoomRepository

class AllCreaturesViewModel(private val repository: CreatureRepository = RoomRepository()) : ViewModel() {

  private val allCreaturesLiveData = repository.getAllCreatures()

  fun getAllCreaturesLiveData() = allCreaturesLiveData

  fun clearAllCreatures() = repository.clearAllCreatures()
}