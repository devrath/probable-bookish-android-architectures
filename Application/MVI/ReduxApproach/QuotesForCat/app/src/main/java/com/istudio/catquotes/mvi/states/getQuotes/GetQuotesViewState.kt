package com.istudio.catquotes.mvi.states.getQuotes

import com.istudio.catquotes.model.AnimalResponse
import com.istudio.catquotes.mvi.mvibase.interfaces.MviViewState

data class GetQuotesViewState (
    val isLoading : Boolean,
    val quotes : List<AnimalResponse>,
    val error : Throwable?
) : MviViewState {

    companion object {
        fun idle() : GetQuotesViewState = GetQuotesViewState (
            isLoading = true,
            quotes = emptyList(),
            error = null
        )
    }


}