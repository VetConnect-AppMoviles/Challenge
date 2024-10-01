package com.example.app_project_challenge.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_project_challenge.R

class MainActivity : AppCompatActivity() {
    private lateinit var pokemonBtn : Button
    private lateinit var favoriteBtn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonBtn = findViewById(R.id.btPokemon)
        favoriteBtn = findViewById(R.id.btFavorite)


        pokemonBtn.setOnClickListener {
            val intent = Intent(this, PokemonActivity::class.java)
            startActivity(intent)
        }

        favoriteBtn.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}