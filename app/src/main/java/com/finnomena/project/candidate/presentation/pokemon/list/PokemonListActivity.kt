package com.finnomena.project.candidate.presentation.pokemon.list

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.presentation.pokemon.detail.PokemonDetailActivity
import com.finnomena.project.candidate.utils.UtilsUI.Companion.hideKeyboard
import kotlinx.android.synthetic.main.activity_pokemon_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Ungkrit Jullawut
 * Finnomena Assignment
 */
class PokemonListActivity : AppCompatActivity() {
    private val viewModel: PokemonListViewModel by viewModel()
    private var mAdapter: PokemonListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_search)
        title = "Pokemon List"
        init()
    }

    private fun init() {
        hideKeyboard()
        initSetAdapterEvent()
        initGetData()
    }

    private fun initSetAdapterEvent() {
        mAdapter = PokemonListAdapter()
        viewModel.dataList.observe(this, Observer { dataList ->
            if (dataList.isNotEmpty()) {
                rvItems.apply {
                    layoutManager = LinearLayoutManager(this@PokemonListActivity)
                    adapter = mAdapter?.apply {
                        if (edtAutocomplete.text.toString().isNotBlank()) {
                            fullItems = dataList
                            mAdapter?.filter?.filter(edtAutocomplete.text.toString())
                        } else {
                            fullItems = dataList
                            items = dataList
                        }.also {
                            swipeContainer.isRefreshing = false
                        }
                        callBackOnClick = { id ->
                            val intent = Intent(this@PokemonListActivity, PokemonDetailActivity::class.java)
                            intent.putExtra("pokemonId", id)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                        }

                    }

                }
            }
        })

        edtAutocomplete.doOnTextChanged { text, _, _, _ ->
            mAdapter?.filter?.filter(text)
        }

        swipeContainer.setOnRefreshListener {
            getPokemon()
        }

    }

    private fun initGetData() {
        getPokemon()
    }

    private fun getPokemon() {
        viewModel.getPokemon().observe(this, Observer { dataResponse ->
            viewModel.dataList.value = dataResponse
        })
    }

}
