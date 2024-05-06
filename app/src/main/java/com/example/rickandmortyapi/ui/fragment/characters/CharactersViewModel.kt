package com.example.rickandmortyapi.ui.fragment.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.repository.CharactersRepository
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
) : ViewModel() {
    fun getCharacter(): LiveData<Resource<List<Character>>> = repository.getCharacter()
}