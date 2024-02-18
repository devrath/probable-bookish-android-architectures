package com.istudio.catapp.states

sealed class MainIntent {

    data object FetchQuote: MainIntent()

}