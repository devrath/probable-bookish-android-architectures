package com.istudio.catquotes.mvibase

import com.istudio.catquotes.mvibase.interfaces.MviIntent
import com.istudio.catquotes.mvibase.interfaces.MviViewState
import kotlinx.coroutines.flow.Flow

/**
 * Takes the intent from the view & converts into a view state
 */
interface MviView<I : MviIntent, in S : MviViewState> {
  fun intents(): Flow<I>
  fun render(state: S)
}