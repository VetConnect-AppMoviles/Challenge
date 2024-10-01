package com.example.app_project_challenge.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_project_challenge.R
import com.example.app_project_challenge.adapters.PokemonAdapter
import com.example.app_project_challenge.db.AppDatabase
import com.example.app_project_challenge.models.Pokemon

class FavoriteActivity : AppCompatActivity(), PokemonAdapter.OnItemClickListener {
    private lateinit var rvFavorite : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setSupportActionBar(findViewById(R.id.toolbar2))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        rvFavorite = findViewById(R.id.rvFavorite)
    }

    override fun onResume() {
        super.onResume()

        loadPokemons { pokemons ->
            rvFavorite.adapter = PokemonAdapter(pokemons, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    private fun loadPokemons(onComplete: (List<Pokemon>)-> Unit) {
        val dao = AppDatabase.getInstance(this).getDao()

        onComplete(dao.getAll())
    }

    override fun onItemClick(pokemon: Pokemon) {
        val dao = AppDatabase.getInstance(this).getDao()
        dao.delete(pokemon)

        Toast.makeText(this, "Pokemon " + pokemon.name + " deleted from favorites", Toast.LENGTH_SHORT).show()

        loadPokemons { pokemons ->
            rvFavorite.adapter = PokemonAdapter(pokemons, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

}