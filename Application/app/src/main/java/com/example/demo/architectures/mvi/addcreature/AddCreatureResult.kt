package com.example.demo.architectures.mvi.addcreature

import com.example.demo.architectures.mvi.mvibase.MviResult


sealed class AddCreatureResult : MviResult {
  sealed class AvatarResult : AddCreatureResult() {
    object Processing : AvatarResult()
    data class Success(val drawable: Int) : AvatarResult()
    data class Failure(val error: Throwable) : AvatarResult()
  }
  sealed class NameResult : AddCreatureResult() {
    object Processing : NameResult()
    data class Success(val name: String) : NameResult()
    data class Failure(val error: Throwable) : NameResult()
  }
  sealed class IntelligenceResult : AddCreatureResult() {
    object Processing : IntelligenceResult()
    data class Success(val intelligence: Int) : IntelligenceResult()
    data class Failure(val error: Throwable) : IntelligenceResult()
  }
  sealed class StrengthResult : AddCreatureResult() {
    object Processing : StrengthResult()
    data class Success(val strength: Int) : StrengthResult()
    data class Failure(val error: Throwable) : StrengthResult()
  }
  sealed class EnduranceResult : AddCreatureResult() {
    object Processing : EnduranceResult()
    data class Success(val endurance: Int) : EnduranceResult()
    data class Failure(val error: Throwable) : EnduranceResult()
  }
  sealed class SaveResult : AddCreatureResult() {
    object Processing : SaveResult()
    object Success : SaveResult()
    data class Failure(val error: Throwable) : SaveResult()
  }
}