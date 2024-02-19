package com.istudio.catquotes.mvi.mvibase

import com.istudio.catquotes.mvi.mvibase.interfaces.MviIntent
import com.istudio.catquotes.mvi.mvibase.interfaces.MviViewState
import kotlinx.coroutines.flow.Flow

/**
 * Takes the intent from the view & converts into a state
 */
interface MviViewModel<I : MviIntent, S : MviViewState> {
  fun processIntents(intents: Flow<I>)
  fun states(): Flow<S>
}