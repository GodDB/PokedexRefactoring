package com.example.pokedexrefactoring.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexrefactoring.data.remote.model.Pokemon
import com.example.pokedexrefactoring.databinding.ItemPokemonBinding

class PokemonAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Pokemon, PokemonViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                .apply { vm = viewModel }
        return PokemonViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {

            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}


class PokemonViewHolder(
    private val itemPokemonBinding: ItemPokemonBinding
) : RecyclerView.ViewHolder(itemPokemonBinding.root) {

    fun bind(data: Pokemon, index : Int) {
        itemPokemonBinding.pokemon = data
        itemPokemonBinding.index = index
        itemPokemonBinding.executePendingBindings()
    }
}