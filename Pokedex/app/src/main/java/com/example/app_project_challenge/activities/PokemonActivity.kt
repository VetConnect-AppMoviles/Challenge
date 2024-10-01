package com.example.app_project_challenge.activities
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_project_challenge.R
import com.example.app_project_challenge.adapters.PokemonAdapter
import com.example.app_project_challenge.communication.ApiResponse
import com.example.app_project_challenge.db.AppDatabase
import com.example.app_project_challenge.models.Pokemon
import com.example.app_project_challenge.network.RandomPokemonApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonActivity : AppCompatActivity(), PokemonAdapter.OnItemClickListener {

    private lateinit var btnLoad : Button
    private lateinit var etResults: EditText
    private lateinit var rvPokemon: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        setSupportActionBar(findViewById(R.id.toolbar))
        btnLoad = findViewById(R.id.btLoadPokemon)
        etResults = findViewById(R.id.etPokemonCount)
        rvPokemon = findViewById(R.id.rvPokemon)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        btnLoad.setOnClickListener {
            val results = etResults.text.toString().toInt()
            loadPokemon(results) { pokemons ->
                rvPokemon.adapter = PokemonAdapter(pokemons, this)
                rvPokemon.layoutManager = LinearLayoutManager(this@PokemonActivity)
            }
        }
    }


    private fun loadPokemon(results : Int, onComplete: (List<Pokemon>) -> Unit) {
        var id = results

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val randomPokemonApiService : RandomPokemonApiService = retrofit.create(RandomPokemonApiService::class.java)

        val request = randomPokemonApiService.getPokemon(id)

        request.enqueue(object: Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val pokemonApiPokemons : ApiResponse = response.body()!!
                    val personList = mutableListOf<Pokemon>()

                    pokemonApiPokemons.id?.forEach {
                        personList.add(it.toPokemon())
                    }

                    onComplete(personList)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    override fun onItemClick(pokemon: Pokemon) {
        val dao = AppDatabase.getInstance(this).getDao()
        dao.insertOne(pokemon)

        Toast.makeText(this, "Pokemon "+pokemon.name+" added to favorites", Toast.LENGTH_SHORT).show()
    }
}