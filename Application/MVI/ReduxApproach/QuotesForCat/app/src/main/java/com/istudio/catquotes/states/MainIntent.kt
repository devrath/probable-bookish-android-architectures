package com.istudio.catquotes.states

sealed class MainIntent {

    data object FetchQuote: MainIntent()

}