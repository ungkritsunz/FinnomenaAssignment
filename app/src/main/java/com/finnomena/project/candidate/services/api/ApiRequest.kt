package com.finnomena.project.candidate.services.api

import com.google.gson.annotations.SerializedName

data class ApiRequest<T>(
    @SerializedName("status")
    var status: T? = null,
    @SerializedName("data")
    var data: T? = null
)
