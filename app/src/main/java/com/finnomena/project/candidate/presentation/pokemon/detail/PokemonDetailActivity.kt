package com.finnomena.project.candidate.presentation.pokemon.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.finnomena.project.candidate.Application
import com.finnomena.project.candidate.R
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Ungkrit Jullawut
 * Finnomena Assignment
 */
class PokemonDetailActivity : AppCompatActivity() {
    private val viewModel: PokemonDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Pokemon Detail"
        init()
    }

    private fun init() {
        val bundle: Bundle? = intent.extras
        bundle?.get("pokemonId")?.let { id ->
            viewModel.getPokemonById(id.toString().toInt()).observe(this, Observer { data ->
                tvName.text = data?.name ?: "-"
                tvHeight.text = data?.height?.toString() ?: "-"
                tvWeight.text = data?.weight?.toString() ?: "-"

                var type = ""
                data?.types?.forEachIndexed { index, dataType ->
                    type += when (index) {
                        0 -> dataType.type?.name
                        else -> ", ${dataType.type?.name}"
                    }
                }
                tvType.text = type

                data?.sprites?.front_default?.let { url ->

                    val circularProgressDrawable =
                        CircularProgressDrawable(Application.mApplicationContext)
                    circularProgressDrawable.strokeWidth = 5f
                    circularProgressDrawable.centerRadius = 30f
                    circularProgressDrawable.start()

                    Glide
                        .with(this)
                        .load(url)
                        .placeholder(circularProgressDrawable)
                        .into(ivPokemon)

                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}