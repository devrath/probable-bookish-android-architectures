package com.example.demo.architectures.mvi.allcreatures

import com.example.demo.architectures.mvi.mvibase.MviIntent

sealed class AllCreaturesIntent : MviIntent {
  object LoadAllCreaturesIntent : AllCreaturesIntent()
  object ClearAllCreaturesIntent : AllCreaturesIntent()
}