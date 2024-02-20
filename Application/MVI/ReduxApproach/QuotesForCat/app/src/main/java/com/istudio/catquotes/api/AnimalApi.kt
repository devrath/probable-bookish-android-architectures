package com.istudio.catquotes.api

import com.istudio.catquotes.model.AnimalResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface AnimalApi {
    @GET("/facts")
    suspend fun getQuotes(): Array<AnimalResponse>
    @GET("/facts")
    fun getQuotesObservable(): Observable<List<AnimalResponse>>
}