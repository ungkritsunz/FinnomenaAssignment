package com.finnomena.project.candidate.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("weight")
    var weight: Int? = null,
    @SerializedName("types")
    var types: List<DataType>? = null,
    @SerializedName("pokemon_entries")
    var pokemon_entries: PokemonEntries? = null,
    @SerializedName("sprites")
    var sprites: Sprites? = null

)