package com.finnomena.project.candidate.services.repository

import androidx.lifecycle.MutableLiveData
import com.finnomena.project.candidate.model.Data
import com.finnomena.project.candidate.model.PokemonEntries

interface Repository {
    fun getPokemon() : MutableLiveData<MutableList<PokemonEntries>>
    fun getPokemonById(id:Int) : MutableLiveData<Data>
}