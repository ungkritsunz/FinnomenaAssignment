package com.finnomena.project.candidate.presentation.pokemon.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.model.PokemonEntries
import com.finnomena.project.candidate.utils.Constants.Companion.VIEWTYPE_EMPTY
import com.finnomena.project.candidate.utils.Constants.Companion.VIEWTYPE_NORMAL
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates


class PokemonListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var items by Delegates.observable(mutableListOf<PokemonEntries>()) { _, _, _ -> notifyDataSetChanged() }
    var fullItems: MutableList<PokemonEntries>? = null
    lateinit var callBackOnClick: (Int) -> Unit

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when {
            items.isNullOrEmpty() -> {
                VIEWTYPE_EMPTY
            }
            else -> {
                VIEWTYPE_NORMAL
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEWTYPE_NORMAL -> {
                ViewHolderNormal(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_pokemon,
                        parent,
                        false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_empty,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].apply {
            when (getItemViewType(position)) {
                VIEWTYPE_NORMAL -> {
                    holder.apply {

                    }
                    (holder as ViewHolderNormal).bindNormal(this)
                }
                VIEWTYPE_EMPTY -> (holder as EmptyViewHolder)
            }
        }
    }


    inner class ViewHolderNormal(view: View) : RecyclerView.ViewHolder(view) {
        private val headerItem: TextView = view.tvHeaderItem
        private val clItemPokemonItem: ConstraintLayout = view.clItemPokemon
        fun bindNormal(dataObj: PokemonEntries) {
            dataObj.apply {
                headerItem.text = pokemon_species?.name ?: "-"
                clItemPokemonItem.setOnClickListener {
                    entry_number?.let { it1 -> callBackOnClick.invoke(it1) }
                }
            }
        }
    }

    class EmptyViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSeq: CharSequence?): FilterResults {
                var listType: MutableList<PokemonEntries> = ArrayList()
                val strChange = charSeq.toString()
                if (strChange.isBlank()) {
                    if (fullItems != null)
                        listType = fullItems!!
                } else {
                    fullItems?.forEach { data ->
                        if (filterTextContains(strChange, data))
                            listType.add(data)
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = listType
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                items = filterResults.values as MutableList<PokemonEntries>
            }
        }
    }

    // Search box filter by id, name
    @SuppressLint("DefaultLocale")
    private fun filterTextContains(textChange: String, data: PokemonEntries): Boolean {
        return when (true) {
            data.pokemon_species?.name?.toLowerCase()?.contains(textChange.toLowerCase()) -> true
            data.entry_number?.toString()?.toLowerCase()?.contains(textChange.toLowerCase()) -> true
            else -> false
        }
    }

}




