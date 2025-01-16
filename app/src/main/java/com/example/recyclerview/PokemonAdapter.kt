package com.example.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityItemPokemonBinding

class PokemonAdapter (private var listaPokemon: MutableList<Pokemon>, private val listener: OnClickListener): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    //Es una clase que mantiene los elementos de la lista en memoria
    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val binding = ActivityItemPokemonBinding.bind(view) //Para acceder a los elementos del layout del item
        fun setListener(pokemon: Pokemon){
            binding.root.setOnLongClickListener{
                listener.onLongClick(pokemon)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_pokemon, parent, false)
        return ViewHolder(view)
    }

    //Es la función que se ejecuta cada vez que un elemento es visible. El recycler le inyecta los datos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Le damos un valor a los elementos de la lista
        val pokemon = listaPokemon.get(position)
        holder.setListener(pokemon)
        holder.binding.tituloPokemon.text = pokemon.nombre
        holder.binding.check.isChecked=pokemon.atrapado

        holder.binding.check.setOnClickListener {
            pokemon.atrapado = holder.binding.check.isChecked
        }
        if (position % 2 == 0){
            holder.binding.root.setBackgroundColor(

                holder.binding.root.resources.getColor(R.color.rojito, null)
            )
        }

    }

    fun addPokemon(pokemon: Pokemon){
        listaPokemon.add(pokemon)
        notifyDataSetChanged()
    }

    fun removePokemon(pokemon:Pokemon){
        listaPokemon.remove(pokemon)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = listaPokemon.size


}