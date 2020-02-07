package com.finnomena.project.candidate.model

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
)