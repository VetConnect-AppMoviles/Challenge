package com.example.app_project_challenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.app_project_challenge.R
import com.example.app_project_challenge.models.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(private val pokemons: List<Pokemon>, private val clickListener: OnItemClickListener) : Adapter<PokemonAdapter.PokemonViewHolder>(){
    inner class PokemonViewHolder(itemView: View) : ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvOrder: TextView = itemView.findViewById(R.id.tvOrder)
        private val ivPokemon: ImageView = itemView.findViewById(R.id.ivPokemon)
        private val like: ImageButton = itemView.findViewById(R.id.btLike)

        fun bind(pokemon: Pokemon, clickListener: OnItemClickListener) {
            tvName.text = pokemon.name
            tvOrder.text = pokemon.order.toString()

            Picasso.get()
                .load(pokemon.sprite)
                .into(ivPokemon)
            like.setOnClickListener {
                clickListener.onItemClick(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position], clickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(pokemon: Pokemon)
    }
}
