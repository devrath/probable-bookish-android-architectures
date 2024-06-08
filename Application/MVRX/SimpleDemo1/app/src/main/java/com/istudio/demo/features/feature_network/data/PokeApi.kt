package com.istudio.demo.features.feature_network.data


import com.istudio.demo.features.feature_network.models.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonList

}