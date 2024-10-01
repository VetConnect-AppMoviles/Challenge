package com.example.app_project_challenge.network

import com.example.app_project_challenge.communication.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RandomPokemonApiService {
    @GET("pokemon/{id}/")
    fun getPokemon(@Path("id") id: Int): Call<ApiResponse>
}