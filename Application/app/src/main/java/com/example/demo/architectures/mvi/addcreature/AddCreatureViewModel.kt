package com.example.demo.architectures.mvi.addcreature

import androidx.lifecycle.ViewModel
import com.example.demo.architectures.mvi.data.model.CreatureAttributes
import com.example.demo.architectures.mvi.data.model.CreatureGenerator
import com.example.demo.architectures.mvi.mvibase.MviViewModel
import com.raywenderlich.android.creaturemon.addcreature.AddCreatureProcessorHolder
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

class AddCreatureViewModel(
    private val actionProcessorHolder: AddCreatureProcessorHolder
) : ViewModel(), MviViewModel<AddCreatureIntent, AddCreatureViewState> {

  private val intentsSubject: PublishSubject<AddCreatureIntent> = PublishSubject.create()
  private val statesObservable: Observable<AddCreatureViewState> = compose()

  override fun processIntents(intents: Observable<AddCreatureIntent>) {
    intents.subscribe(intentsSubject)
  }

  override fun states(): Observable<AddCreatureViewState> = statesObservable

  private fun compose(): Observable<AddCreatureViewState> {
    return intentsSubject
        .map(this::actionFromIntent)
        .compose(actionProcessorHolder.actionProcessor)
        .scan(AddCreatureViewState.default(), reducer)
        .distinctUntilChanged()
        .replay(1)
        .autoConnect(0)
  }

  private fun actionFromIntent(intent: AddCreatureIntent): AddCreatureAction {
    return when (intent) {
      is AddCreatureIntent.AvatarIntent -> AddCreatureAction.AvatarAction(intent.drawable)
      is AddCreatureIntent.NameIntent -> AddCreatureAction.NameAction(intent.name)
      is AddCreatureIntent.IntelligenceIntent -> AddCreatureAction.IntelligenceAction(intent.intelligenceIndex)
      is AddCreatureIntent.StrengthIntent -> AddCreatureAction.StrengthAction(intent.strengthIndex)
      is AddCreatureIntent.EnduranceIntent -> AddCreatureAction.EnduranceAction(intent.enduranceIndex)
      is AddCreatureIntent.SaveIntent -> AddCreatureAction.SaveAction(
          intent.drawable, intent.name, intent.intelligenceIndex, intent.strengthIndex, intent.enduranceIndex)
    }
  }

  companion object {
    private val generator = CreatureGenerator()

    private val reducer = BiFunction { previousState: AddCreatureViewState, result: AddCreatureResult ->
      when (result) {
        is AddCreatureResult.AvatarResult -> reduceAvatar(previousState, result)
        is AddCreatureResult.NameResult -> reduceName(previousState, result)
        is AddCreatureResult.IntelligenceResult -> reduceIntelligence(previousState, result)
        is AddCreatureResult.StrengthResult -> reduceStrength(previousState, result)
        is AddCreatureResult.EnduranceResult -> reduceEndurance(previousState, result)
        is AddCreatureResult.SaveResult -> reduceSave(previousState, result)
      }
    }

    private fun reduceAvatar(
        previousState: AddCreatureViewState,
        result: AddCreatureResult.AvatarResult
    )
        : AddCreatureViewState = when (result) {
      is AddCreatureResult.AvatarResult.Success -> {
        previousState.copy(
            isProcessing = false,
            error = null,
            creature = generator.generateCreature(
                previousState.creature.attributes, previousState.creature.name, result.drawable),
            isDrawableSelected = (result.drawable != 0))
      }
      is AddCreatureResult.AvatarResult.Failure -> {
        previousState.copy(isProcessing = false, error = result.error)
      }
      is AddCreatureResult.AvatarResult.Processing -> {
        previousState.copy(isProcessing = true, error = null)
      }
    }

    private fun reduceName(
        previousState: AddCreatureViewState,
        result: AddCreatureResult.NameResult
    )
        : AddCreatureViewState = when (result) {
      is AddCreatureResult.NameResult.Success -> {
        previousState.copy(
            isProcessing = false,
            error = null,
            creature = generator.generateCreature(
                previousState.creature.attributes, result.name, previousState.creature.drawable))
      }
      is AddCreatureResult.NameResult.Failure -> {
        previousState.copy(isProcessing = false, error = result.error)
      }
      is AddCreatureResult.NameResult.Processing -> {
        previousState.copy(isProcessing = true, error = null)
      }
    }

    private fun reduceIntelligence(
        previousState: AddCreatureViewState,
        result: AddCreatureResult.IntelligenceResult
    )
        : AddCreatureViewState = when (result) {
      is AddCreatureResult.IntelligenceResult.Success -> {
        val attributes = CreatureAttributes(
            result.intelligence,
            previousState.creature.attributes.strength,
            previousState.creature.attributes.endurance)
        previousState.copy(
            isProcessing = false,
            error = null,
            creature = generator.generateCreature(
                attributes, previousState.creature.name, previousState.creature.drawable))
      }
      is AddCreatureResult.IntelligenceResult.Failure -> {
        previousState.copy(isProcessing = false, error = result.error)
      }
      is AddCreatureResult.IntelligenceResult.Processing -> {
        previousState.copy(isProcessing = true, error = null)
      }
    }

    private fun reduceStrength(
        previousState: AddCreatureViewState,
        result: AddCreatureResult.StrengthResult
    )
        : AddCreatureViewState = when (result) {
      is AddCreatureResult.StrengthResult.Success -> {
        val attributes = CreatureAttributes(
            previousState.creature.attributes.intelligence,
            result.strength,
            previousState.creature.attributes.endurance)
        previousState.copy(
            isProcessing = false,
            error = null,
            creature = generator.generateCreature(
                attributes, previousState.creature.name, previousState.creature.drawable))
      }
      is AddCreatureResult.StrengthResult.Failure -> {
        previousState.copy(isProcessing = false, error = result.error)
      }
      is AddCreatureResult.StrengthResult.Processing -> {
        previousState.copy(isProcessing = true, error = null)
      }
    }

    private fun reduceEndurance(
        previousState: AddCreatureViewState,
        result: AddCreatureResult.EnduranceResult)
        : AddCreatureViewState = when (result) {
      is AddCreatureResult.EnduranceResult.Success -> {
        val attributes = CreatureAttributes(
            previousState.creature.attributes.intelligence,
            previousState.creature.attributes.strength,
            result.endurance)
        previousState.copy(
            isProcessing = false,
            error = null,
            creature = generator.generateCreature(
                attributes, previousState.creature.name, previousState.creature.drawable))
      }
      is AddCreatureResult.EnduranceResult.Failure -> {
        previousState.copy(isProcessing = false, error = result.error)
      }
      is AddCreatureResult.EnduranceResult.Processing -> {
        previousState.copy(isProcessing = true, error = null)
      }
    }

    private fun reduceSave(
        previousState: AddCreatureViewState,
        result: AddCreatureResult.SaveResult
    )
        : AddCreatureViewState = when (result) {
      is AddCreatureResult.SaveResult.Success -> {
        previousState.copy(isProcessing = false, isSaveComplete = true, error = null)
      }
      is AddCreatureResult.SaveResult.Failure -> {
        previousState.copy(isProcessing = false, error = result.error)
      }
      is AddCreatureResult.SaveResult.Processing -> {
        previousState.copy(isProcessing = true, error = null)
      }
    }
  }
}