package com.finnomena.project.candidate.services.api

import com.finnomena.project.candidate.model.PokemonEntries
import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("pokemon_entries")
    var pokemon_entries: MutableList<PokemonEntries>? = null
)