package com.finnomena.project.candidate.model

import com.google.gson.annotations.SerializedName

data class DataType(
    @SerializedName("slot")
    var slot: Int? = null,
    @SerializedName("type")
    var type: Type? = null
)