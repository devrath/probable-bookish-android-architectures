package com.example.demo.architectures.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.architectures.commonlayer.model.*

class CreatureViewModel(private val generator: CreatureGenerator = CreatureGenerator()) : ViewModel() {

  private val creatureLiveData = MutableLiveData<Creature>()

  fun getCreatureLiveData(): LiveData<Creature> = creatureLiveData
  var name = ""
  var intelligence = 0
  var strength = 0
  var endurance = 0
  var drawable = 0

  lateinit var creature: Creature

  fun updateCreature() {
    val attributes = CreatureAttributes(intelligence, strength, endurance)
    creature = generator.generateCreature(attributes, name, drawable)
    creatureLiveData.postValue(creature)
  }

  fun attributeSelected(attributeType: AttributeType, position: Int) {
    when (attributeType) {
      AttributeType.INTELLIGENCE ->
        intelligence = AttributeStore.INTELLIGENCE[position].value
      AttributeType.STRENGTH ->
        strength = AttributeStore.STRENGTH[position].value
      AttributeType.ENDURANCE ->
        endurance = AttributeStore.ENDURANCE[position].value
    }
    updateCreature()
  }

  fun drawableSelected(drawable: Int) {
    this.drawable = drawable
    updateCreature()
  }
}