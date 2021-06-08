package com.example.demo.architectures.mvi.allcreatures

import com.example.demo.architectures.mvi.data.model.Creature
import com.example.demo.architectures.mvi.mvibase.MviViewState

data class AllCreaturesViewState(
    val isLoading: Boolean,
    val creatures: List<Creature>,
    val error: Throwable?
) : MviViewState {
  companion object {
    fun idle(): AllCreaturesViewState = AllCreaturesViewState(
        isLoading = false,
        creatures = emptyList(),
        error = null
    )
  }
}