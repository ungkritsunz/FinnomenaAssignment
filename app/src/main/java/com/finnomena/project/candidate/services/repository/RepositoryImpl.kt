package com.finnomena.project.candidate.services.repository

import androidx.lifecycle.MutableLiveData
import com.finnomena.project.candidate.model.Data
import com.finnomena.project.candidate.model.PokemonEntries
import com.finnomena.project.candidate.services.api.ApiInterface
import com.finnomena.project.candidate.services.ServiceManager
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get()) }
}

class RepositoryImpl(private val remoteDataSource: ApiInterface) : Repository {

    override fun getPokemon(): MutableLiveData<MutableList<PokemonEntries>>  {
        val data: MutableLiveData<MutableList<PokemonEntries>> = MutableLiveData()
        ServiceManager.service(
            call = remoteDataSource.getPokemon()
        ) { dataResponse ->
            dataResponse?.let {
                data.value = it.pokemon_entries
            }
        }
        return data
    }

    override fun getPokemonById(id: Int): MutableLiveData<Data> {
        val data: MutableLiveData<Data> = MutableLiveData()
        ServiceManager.service(
            call = remoteDataSource.getPokemonById(id)
        ) { dataResponse ->
            dataResponse?.let {
                data.value = it
            }
        }
        return data
    }
}