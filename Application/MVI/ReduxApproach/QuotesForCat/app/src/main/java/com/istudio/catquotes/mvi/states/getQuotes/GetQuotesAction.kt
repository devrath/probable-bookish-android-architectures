package com.istudio.catquotes.mvi.states.getQuotes

import com.istudio.catquotes.mvi.mvibase.interfaces.MviAction

sealed class GetQuotesAction : MviAction {
    data object RetrieveQuotesAction : GetQuotesAction()
}