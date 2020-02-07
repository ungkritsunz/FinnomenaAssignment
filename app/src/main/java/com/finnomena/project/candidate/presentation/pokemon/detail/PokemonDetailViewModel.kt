package com.finnomena.project.candidate.presentation.pokemon.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finnomena.project.candidate.model.Data
import com.finnomena.project.candidate.services.repository.Repository

class PokemonDetailViewModel(private val repository: Repository) : ViewModel() {

    fun getPokemonById(id: Int): MutableLiveData<Data> {
        return repository.getPokemonById(id)
    }

}