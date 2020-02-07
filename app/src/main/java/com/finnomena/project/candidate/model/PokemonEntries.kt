package com.finnomena.project.candidate.model

import com.google.gson.annotations.SerializedName

data class PokemonEntries(
    @SerializedName("entry_number")
    var entry_number: Int? = null,
    @SerializedName("pokemon_species")
    var pokemon_species: PokemonSpecies? = null
)