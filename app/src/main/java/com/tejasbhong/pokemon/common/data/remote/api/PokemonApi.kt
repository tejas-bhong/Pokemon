package com.tejasbhong.pokemon.common.data.remote.api

import com.tejasbhong.pokemon.common.data.remote.api.model.PokemonDetailsResponse
import com.tejasbhong.pokemon.common.data.remote.api.model.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonsResponse

    @GET
    suspend fun getPokemons(
        @Url url: String,
    ): PokemonsResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int,
    ): PokemonDetailsResponse
}
