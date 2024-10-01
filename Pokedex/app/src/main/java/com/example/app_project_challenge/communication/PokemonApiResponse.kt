package com.example.app_project_challenge.communication

import com.example.app_project_challenge.models.Pokemon

data class PokemonResponse(
    val name: String,
    val sprites: Sprites,
    val order: Int
) {
    fun toPokemon(): Pokemon {
        return Pokemon(
            name = name,
            sprite = sprites.front_default,
            order = order
        )
    }
}

data class Sprites(
    val front_default: String?
)
