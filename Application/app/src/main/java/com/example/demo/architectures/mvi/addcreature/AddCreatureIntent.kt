package com.example.demo.architectures.mvi.addcreature

import com.example.demo.architectures.mvi.mvibase.MviIntent

sealed class AddCreatureIntent : MviIntent {
  data class AvatarIntent(val drawable: Int) : AddCreatureIntent()
  data class NameIntent(val name: String) : AddCreatureIntent()
  data class IntelligenceIntent(val intelligenceIndex: Int) : AddCreatureIntent()
  data class StrengthIntent(val strengthIndex: Int) : AddCreatureIntent()
  data class EnduranceIntent(val enduranceIndex: Int) : AddCreatureIntent()
  data class SaveIntent(
      val drawable: Int,
      val name: String,
      val intelligenceIndex: Int,
      val strengthIndex: Int,
      val enduranceIndex: Int) : AddCreatureIntent()
}