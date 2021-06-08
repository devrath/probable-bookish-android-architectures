package com.example.demo.architectures.mvi.allcreatures

import com.example.demo.architectures.mvi.data.repository.CreatureRepository
import com.example.demo.architectures.mvi.util.schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class AllCreaturesProcessorHolder(
    private val creatureRepository: CreatureRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

  private val loadAllCreaturesProcessor =
      ObservableTransformer<AllCreaturesAction.LoadAllCreaturesAction, AllCreaturesResult.LoadAllCreaturesResult> { actions ->
        actions.flatMap {
          creatureRepository.getAllCreatures()
              .map { creatures -> AllCreaturesResult.LoadAllCreaturesResult.Success(creatures) }
              .cast(AllCreaturesResult.LoadAllCreaturesResult::class.java)
              .onErrorReturn(AllCreaturesResult.LoadAllCreaturesResult::Failure)
              .subscribeOn(schedulerProvider.io())
              .observeOn(schedulerProvider.ui())
              .startWith(AllCreaturesResult.LoadAllCreaturesResult.Loading)
        }
      }

  private val clearAllCreaturesProcessor =
      ObservableTransformer<AllCreaturesAction.ClearAllCreaturesAction, AllCreaturesResult.ClearAllCreaturesResult> { actions ->
        actions.flatMap {
          creatureRepository.clearAllCreatures()
              .map { AllCreaturesResult.ClearAllCreaturesResult.Success }
              .cast(AllCreaturesResult.ClearAllCreaturesResult::class.java)
              .onErrorReturn(AllCreaturesResult.ClearAllCreaturesResult::Failure)
              .subscribeOn(schedulerProvider.io())
              .observeOn(schedulerProvider.ui())
              .startWith(AllCreaturesResult.ClearAllCreaturesResult.Clearing)
        }
      }

  internal var actionProcessor =
      ObservableTransformer<AllCreaturesAction, AllCreaturesResult> { actions ->
        actions.publish { shared ->
          Observable.merge(
              shared.ofType(AllCreaturesAction.LoadAllCreaturesAction::class.java).compose(loadAllCreaturesProcessor),
              shared.ofType(AllCreaturesAction.ClearAllCreaturesAction::class.java).compose(clearAllCreaturesProcessor))
              .mergeWith(
                  // Error for not implemented actions
                  shared.filter { v ->
                    v !is AllCreaturesAction.LoadAllCreaturesAction
                        && v !is AllCreaturesAction.ClearAllCreaturesAction
                  }.flatMap { w ->
                    Observable.error(
                        IllegalArgumentException("Unknown Action type: $w"))
                  }
              )
        }
      }
}