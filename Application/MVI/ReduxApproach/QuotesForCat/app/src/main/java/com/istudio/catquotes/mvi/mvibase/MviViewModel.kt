package com.istudio.catquotes.mvi.mvibase

import com.istudio.catquotes.mvi.mvibase.interfaces.MviIntent
import com.istudio.catquotes.mvi.mvibase.interfaces.MviViewState
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

/**
 * Takes the intent from the view & converts into a state
 * View model must be able to --->
 * * *  (1) Process the intents coming from the view
 * * *  (2) Provide a stream of states for the view to observe
 */
interface MviViewModel<I : MviIntent, S : MviViewState> {
  fun processIntents(intents: Observable<I>)
  fun states(): Observable<S>
}