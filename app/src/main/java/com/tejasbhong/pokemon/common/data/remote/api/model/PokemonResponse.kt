package com.tejasbhong.pokemon.common.data.remote.api.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)
