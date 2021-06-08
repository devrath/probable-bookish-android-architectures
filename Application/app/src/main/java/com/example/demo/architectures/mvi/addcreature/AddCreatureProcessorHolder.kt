package com.raywenderlich.android.creaturemon.addcreature

import com.example.demo.architectures.mvi.addcreature.AddCreatureAction
import com.example.demo.architectures.mvi.addcreature.AddCreatureResult
import com.example.demo.architectures.mvi.addcreature.AddCreatureResult.*
import com.example.demo.architectures.mvi.data.model.AttributeStore
import com.example.demo.architectures.mvi.data.model.CreatureAttributes
import com.example.demo.architectures.mvi.data.model.CreatureGenerator
import com.example.demo.architectures.mvi.data.repository.CreatureRepository
import com.example.demo.architectures.mvi.util.schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class AddCreatureProcessorHolder(
    private val creatureRepository: CreatureRepository,
    private val creatureGenerator: CreatureGenerator,
    private val schedulerProvider: BaseSchedulerProvider
) {

  private val avatarProcessor =
      ObservableTransformer<AddCreatureAction.AvatarAction, AvatarResult> { actions ->
        actions
            .map { action -> AvatarResult.Success(action.drawable) }
            .cast(AvatarResult::class.java)
            .onErrorReturn(AvatarResult::Failure)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .startWith(AvatarResult.Processing)
      }

  private val nameProcessor =
      ObservableTransformer<AddCreatureAction.NameAction, NameResult> { actions ->
        actions
            .map { action -> NameResult.Success(action.name) }
            .cast(NameResult::class.java)
            .onErrorReturn(NameResult::Failure)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .startWith(NameResult.Processing)
      }

  private val intelligenceProcessor =
      ObservableTransformer<AddCreatureAction.IntelligenceAction, IntelligenceResult> { actions ->
        actions
            .map { action ->
              IntelligenceResult.Success(
                  AttributeStore.INTELLIGENCE[action.intelligenceIndex].value)
            }
            .cast(IntelligenceResult::class.java)
            .onErrorReturn(IntelligenceResult::Failure)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .startWith(IntelligenceResult.Processing)
      }

  private val strengthProcessor =
      ObservableTransformer<AddCreatureAction.StrengthAction, StrengthResult> { actions ->
        actions
            .map { action ->
              StrengthResult.Success(
                  AttributeStore.STRENGTH[action.strengthIndex].value)
            }
            .cast(StrengthResult::class.java)
            .onErrorReturn(StrengthResult::Failure)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .startWith(StrengthResult.Processing)
      }

    private val enduranceProcessor =
        ObservableTransformer<AddCreatureAction.EnduranceAction, EnduranceResult> { actions ->
            actions
                .map { action ->
                    EnduranceResult.Success(
                        AttributeStore.ENDURANCE[action.enduranceIndex].value)
                }
                .cast(EnduranceResult::class.java)
                .onErrorReturn(EnduranceResult::Failure)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .startWith(EnduranceResult.Processing)
        }

  private val saveProcessor =
      ObservableTransformer<AddCreatureAction.SaveAction, SaveResult> { actions ->
        actions.flatMap { action ->
          val attributes = CreatureAttributes(
              AttributeStore.INTELLIGENCE[action.intelligenceIndex].value,
              AttributeStore.STRENGTH[action.strengthIndex].value,
              AttributeStore.ENDURANCE[action.enduranceIndex].value)
          val creature = creatureGenerator.generateCreature(attributes, action.name, action.drawable)
          creatureRepository.saveCreature(creature)
              .map { SaveResult.Success }
              .cast(SaveResult::class.java)
              .onErrorReturn(SaveResult::Failure)
              .subscribeOn(schedulerProvider.io())
              .observeOn(schedulerProvider.ui())
              .startWith(SaveResult.Processing)
        }
      }

  internal var actionProcessor =
      ObservableTransformer<AddCreatureAction, AddCreatureResult> { actions ->
        actions.publish { shared ->
          Observable.merge(
              shared.ofType(AddCreatureAction.AvatarAction::class.java).compose(avatarProcessor),
              shared.ofType(AddCreatureAction.NameAction::class.java).compose(nameProcessor),
              shared.ofType(AddCreatureAction.IntelligenceAction::class.java).compose(intelligenceProcessor),
              shared.ofType(AddCreatureAction.StrengthAction::class.java).compose(strengthProcessor))
              .mergeWith(shared.ofType(AddCreatureAction.SaveAction::class.java).compose(saveProcessor))
              .mergeWith(
                  // Error for not implemented actions
                  shared.filter { v ->
                    v !is AddCreatureAction.AvatarAction
                        && v !is AddCreatureAction.NameAction
                        && v !is AddCreatureAction.IntelligenceAction
                        && v !is AddCreatureAction.StrengthAction
                        && v !is AddCreatureAction.SaveAction
                  }.flatMap { w ->
                    Observable.error<AddCreatureResult>(
                        IllegalArgumentException("Unknown Action type: $w"))
                  }
              )
        }
      }
}