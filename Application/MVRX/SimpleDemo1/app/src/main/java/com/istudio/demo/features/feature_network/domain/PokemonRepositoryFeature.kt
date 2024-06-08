package com.istudio.demo.features.feature_network.domain

import com.istudio.demo.features.feature_network.models.PokemonList
import com.istudio.demo.utils.Resource

interface PokemonRepositoryFeature {
    suspend fun getPokemonList(): Resource<PokemonList>
}