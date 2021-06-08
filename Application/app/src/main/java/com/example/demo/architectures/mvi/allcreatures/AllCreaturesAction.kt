package com.example.demo.architectures.mvi.allcreatures

import com.example.demo.architectures.mvi.mvibase.MviAction

sealed class AllCreaturesAction : MviAction {
  object LoadAllCreaturesAction : AllCreaturesAction()
  object ClearAllCreaturesAction : AllCreaturesAction()
}