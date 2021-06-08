package com.example.demo.architectures.mvi.mvibase

import io.reactivex.Observable

interface MviView<I : MviIntent, in S : MviViewState> {
  // Intents are provided to the view model from view
  fun intents(): Observable<I>
  // Results are rendered from the view model to the view
  fun render(state: S)
}