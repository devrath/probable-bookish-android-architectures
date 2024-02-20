package com.istudio.catquotes.main.screen_get_quotes.vm

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.catquotes.api.QuotesRepo
import com.istudio.catquotes.states.MainData
import com.istudio.catquotes.states.MainIntent
import com.istudio.catquotes.states.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Each view model will have a process holder property to do the processing
 *
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    val repo: QuotesRepo
) : ViewModel() {

    /** <*********************> Data  and Event states  <*********************> **/
    /**
     *  Holds the data of all the composables in the view
     */
    var viewState: MutableState<MainData> = mutableStateOf(MainData())
        private set

    /**
     * ViewModel sends event and view observes the event
     */
    var state = mutableStateOf<MainState>(MainState.Idle)
        private set

    /**
     * View sends the Intent and ViewModel reacts to it
     */
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    /** <*********************> Data  and Event states  <*********************> **/


    init {
        // We shall initialize the handling the intent part when the view model starts
        handleIntent()
    }

    /** <************> Get the INTENTS from user -- UI Action is invoked from composable <************> **/
    private fun handleIntent() {
        viewModelScope.launch {
            // From channel, We convert into a  flow and observe it ----> Thus then initiating the action to be performed
            userIntent.consumeAsFlow().collect { collector ->
                when (collector) {
                    is MainIntent.FetchQuote -> {
                        val result = repo.getQuotes()
                        Log.d("Tag", result.toString())
                    }
                }
            }
        }
    }
    /** <************> Get the INTENTS from user -- UI Action is invoked from composable <************> **/

}