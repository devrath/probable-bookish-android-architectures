package com.istudio.catquote.states


sealed class MainState {

    data object Idle: MainState()
    data object Loading: MainState()
    data class Success(val data: String?): MainState()
    data class Error(val error: String?): MainState()

}