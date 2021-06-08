package com.example.demo.architectures.mvi.addcreature

import com.example.demo.architectures.mvi.data.model.Creature
import com.example.demo.architectures.mvi.data.model.CreatureAttributes
import com.example.demo.architectures.mvi.data.model.CreatureGenerator
import com.example.demo.architectures.mvi.mvibase.MviViewState

data class AddCreatureViewState(
    val isProcessing: Boolean,
    val creature: Creature,
    val isDrawableSelected: Boolean,
    val isSaveComplete: Boolean,
    val error: Throwable?
) : MviViewState {
  companion object {
    fun default(): AddCreatureViewState = AddCreatureViewState(
        false,
        CreatureGenerator().generateCreature(CreatureAttributes(), name = "", drawable = 0),
        false,
        false,
        null
    )
  }
}