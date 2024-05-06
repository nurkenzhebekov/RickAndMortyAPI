package com.example.rickandmortyapi.ui.fragment.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.repository.CharacterRepository
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    fun getCharacterById(id: Int): LiveData<Resource<Character>> = repository.getCharacterById(id)

}