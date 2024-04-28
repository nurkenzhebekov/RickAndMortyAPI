package com.example.rickandmortyapi.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.ItemCharacterBinding
import com.example.rickandmortyapi.model.CharacterModel
import com.example.rickandmortyapi.ui.fragment.CharacterAdapter.CharacterViewHolder

class CharacterAdapter(var list: ArrayList<CharacterModel>) : Adapter<CharacterViewHolder>() {
    class CharacterViewHolder(var binding: ItemCharacterBinding) : ViewHolder(binding.root) {
        fun bind(model: CharacterModel) {
            with(binding) {
                ivCharacter.load(model.image)
                tvCharacterName.text = model.name
                tvCharacterRace.text = model.species
                tvCharacterLifeStatus.text = model.status

                when (model.status) {
                    "Alive" -> ivLifeStatusIndicator.setImageResource(R.drawable.ic_dot_green)
                    "Dead" -> ivLifeStatusIndicator.setImageResource(R.drawable.ic_dot_red)
                    else -> ivLifeStatusIndicator.setImageResource(R.drawable.ic_dot_grey)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }
}