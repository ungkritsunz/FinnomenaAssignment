package com.finnomena.project.candidate.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default")
    var front_default: String? = null
)