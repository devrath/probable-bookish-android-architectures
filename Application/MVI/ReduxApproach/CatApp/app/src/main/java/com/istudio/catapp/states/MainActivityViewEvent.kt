package com.istudio.catapp.states

sealed class MainActivityViewEvent {
    data object CurrencyInputTypeValidationInitiate : MainActivityViewEvent()
}