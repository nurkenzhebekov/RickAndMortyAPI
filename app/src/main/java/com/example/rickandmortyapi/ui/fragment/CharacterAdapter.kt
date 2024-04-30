package com.example.rickandmortyapi.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.ItemCharacterBinding
import com.example.rickandmortyapi.data.model.Character

class CharacterAdapter : ListAdapter<Character, CharacterViewHolder>(
    CharacterItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CharacterViewHolder(var binding: ItemCharacterBinding) : ViewHolder(binding.root) {
    fun bind(model: Character) {
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

class CharacterItemCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character) =
        oldItem == newItem

}