package com.example.demo.architectures.mvi.mvibase

import io.reactivex.Observable

interface MviViewModel<I : MviIntent, S : MviViewState> {
  // View model processes the intents
  fun processIntents(intents: Observable<I>)
  // View model outputs the states
  fun states(): Observable<S>
}