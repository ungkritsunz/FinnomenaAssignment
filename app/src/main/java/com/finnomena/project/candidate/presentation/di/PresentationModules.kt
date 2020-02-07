package com.finnomena.project.candidate.presentation.di

import com.finnomena.project.candidate.presentation.pokemon.detail.PokemonDetailViewModel
import com.finnomena.project.candidate.presentation.pokemon.list.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
        viewModel { PokemonListViewModel(get()) }
        viewModel { PokemonDetailViewModel(get()) }
}