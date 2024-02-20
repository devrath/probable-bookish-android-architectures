package com.istudio.catquotes.mvi.mvibase

import com.istudio.catquotes.mvi.mvibase.interfaces.MviIntent
import com.istudio.catquotes.mvi.mvibase.interfaces.MviViewState
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

/**
 * Takes the intent from the view & converts into a view state
 * View must be able to --->
 * * *  (1) Provide intents to the view model
 * * *  (2) Render new state coming from the view model
 */
interface MviView<I : MviIntent, in S : MviViewState> {
  fun intents(): Observable<I>
  fun render(state: S)
}