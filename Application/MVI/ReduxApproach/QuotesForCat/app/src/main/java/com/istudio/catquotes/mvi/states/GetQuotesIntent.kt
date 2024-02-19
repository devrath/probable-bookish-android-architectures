package com.istudio.catquotes.mvi.states

import com.istudio.catquotes.mvi.mvibase.interfaces.MviIntent

sealed class GetQuotesIntent : MviIntent{
    data object RetrieveQuotesIntent: GetQuotesIntent()
}