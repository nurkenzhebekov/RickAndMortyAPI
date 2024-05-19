package com.example.rickandmortyapi.ui.fragment.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.repository.CharacterRepository
import com.example.rickandmortyapi.utils.Resource

class CharacterViewModel (
    private val repository: CharacterRepository
) : ViewModel() {
    fun getCharacterById(id: Int): LiveData<Resource<Character>> = repository.getCharacterById(id)

}