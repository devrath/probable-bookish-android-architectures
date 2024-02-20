package com.istudio.catquotes.mvi.states.getQuotes

import com.istudio.catquotes.model.AnimalResponse
import com.istudio.catquotes.mvi.mvibase.interfaces.MviResult

sealed class GetQuotesResult : MviResult {
    sealed class LoadGetQuotesResult : GetQuotesResult() {
        data object Loading : LoadGetQuotesResult()
        data class Success(val animalResponse : List<AnimalResponse>) : LoadGetQuotesResult()
        data class Failure(val error : Throwable) : LoadGetQuotesResult()
    }
}