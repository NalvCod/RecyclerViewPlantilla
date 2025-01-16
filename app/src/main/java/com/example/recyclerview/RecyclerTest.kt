package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityRecyclerTestBinding

class RecyclerTest : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityRecyclerTestBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        val listaPokemon: MutableList<Pokemon> = mutableListOf(
            Pokemon("Pikachu", 55, "Eléctrico", 10),
            Pokemon("Charmander", 52, "Fuego", 8),
            Pokemon("Bulbasaur", 49, "Planta/Veneno", 9),
            Pokemon("Squirtle", 48, "Agua", 7),
            Pokemon("Jigglypuff", 40, "Hada", 6),
            Pokemon("Meowth", 45, "Normal", 7),
            Pokemon("Eevee", 50, "Normal", 12),
            Pokemon("Snorlax", 110, "Normal", 15),
            Pokemon("Squirtle", 48, "Agua", 7),
            Pokemon("Machop", 70, "Lucha", 14),
            Pokemon("Gengar", 120, "Fantasma/Veneno", 20),
            Pokemon("Lucario", 130, "Lucha/Acero", 18),
            Pokemon("Zubat", 40, "Veneno/Volador", 8),
            Pokemon("Pidgey", 45, "Normal/Volador", 5)
        )


        pokemonAdapter = PokemonAdapter(listaPokemon, this)

        binding.recycler.apply {
            //El contexto dentro del apply es el del recycler
            layoutManager = LinearLayoutManager(this@RecyclerTest)
            adapter = pokemonAdapter
        }

        binding.add.setOnClickListener{
            if (binding.buscadorPokemon.text.toString().isNotEmpty()){
                val pokemon = Pokemon (binding.buscadorPokemon.text.toString())
                addPokemonAutomatically(pokemon)
            }
        }

    }

    private fun addPokemonAutomatically(pokemon : Pokemon){
        pokemonAdapter.addPokemon(pokemon)
    }

    private fun removePokemonAutomatically(pokemon:Pokemon){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Pokémon")
        builder.setMessage("¿Quieres eliminar el Pokémon?")
        builder.setPositiveButton("Si"){ _,_ ->
            pokemonAdapter.removePokemon(pokemon)
        }
        builder.setNegativeButton("No"){_,_ ->
        }
        builder.show()
    }

    override fun onLongClick(pokemon: Pokemon) {
        removePokemonAutomatically(pokemon)
    }
}