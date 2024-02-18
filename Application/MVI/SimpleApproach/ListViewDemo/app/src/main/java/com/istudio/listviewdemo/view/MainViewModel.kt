package com.istudio.listviewdemo.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.listviewdemo.api.AnimalRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: AnimalRepo): ViewModel() {

    /**
     * View will communicate with View-Model using the userIntent(In our case getting the animals data from the API)
     * We have kept it UNLIMITED because, Asuming any number of times view can call this
     */
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    // Private set because no observer should update it
    var state = mutableStateOf<MainState>(MainState.Idle)
        private set

    init {
        // We shall initialize the handling the intent part when the view model starts
        handleIntent()
    }

    /**
     * Get the INTENTS from user
     */
    private fun handleIntent() {
        viewModelScope.launch {
            // From channel, We convert into a  flow and observe it ----> Thus then initiating the action to be performed
            userIntent.consumeAsFlow().collect { collector ->
                when (collector) {
                    is MainIntent.FetchAnimals -> fetchAnimals()
                }
            }
        }
    }

    /**
     * Fetch animals
     * Set the STATES as per the response
     */
    private fun fetchAnimals() {
        viewModelScope.launch {
            // Indicate the loading - State
            state.value = MainState.Loading

            state.value = try {
                // Indicate the data is present - State
                MainState.Animals(repo.getAnimals())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}





