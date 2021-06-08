package com.example.demo.architectures.mvi.addcreature

sealed class AddCreatureAction {
  data class AvatarAction(val drawable: Int) : AddCreatureAction()
  data class NameAction(val name: String) : AddCreatureAction()
  data class IntelligenceAction(val intelligenceIndex: Int) : AddCreatureAction()
  data class StrengthAction(val strengthIndex: Int) : AddCreatureAction()
  data class EnduranceAction(val enduranceIndex: Int) : AddCreatureAction()
  data class SaveAction(
      val drawable: Int,
      val name: String,
      val intelligenceIndex: Int,
      val strengthIndex: Int,
      val enduranceIndex: Int
  ) : AddCreatureAction()
}