package com.istudio.catquote.states

sealed class MainIntent {

    data object FetchQuote: MainIntent()

}