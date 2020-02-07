package com.finnomena.project.candidate.presentation.pokemon.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finnomena.project.candidate.model.PokemonEntries
import com.finnomena.project.candidate.services.repository.Repository

class PokemonListViewModel(private val repository: Repository) : ViewModel() {
    var dataList: MutableLiveData<MutableList<PokemonEntries>> = MutableLiveData()
    fun getPokemon(): MutableLiveData<MutableList<PokemonEntries>> {
        return repository.getPokemon()
    }
}