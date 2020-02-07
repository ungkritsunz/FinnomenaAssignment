package com.finnomena.project.candidate.services.api

import com.finnomena.project.candidate.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("v2/pokedex/2/")
    fun getPokemon(): Call<ApiResponse<Data>>

    @GET("v2/pokemon/{id}")
    fun getPokemonById(@Path("id") id : Int): Call<Data>

}