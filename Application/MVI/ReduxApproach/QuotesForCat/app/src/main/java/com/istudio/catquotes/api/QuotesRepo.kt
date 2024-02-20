package com.istudio.catquotes.api

import javax.inject.Inject

class QuotesRepo @Inject constructor(private val api: AnimalApi) {
    suspend fun getQuotes() = api.getQuotes()
    fun getQuotesObservable() = api.getQuotesObservable()
}