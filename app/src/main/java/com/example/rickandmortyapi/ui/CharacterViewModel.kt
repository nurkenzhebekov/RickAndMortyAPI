package com.example.rickandmortyapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.model.CharacterDetail
import com.example.rickandmortyapi.data.repository.Repository
import com.example.rickandmortyapi.data.model.CharacterList
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getCharacter(): LiveData<Resource<List<Character>>> = repository.getCharacter()

    fun getCharacterById(id: Int): LiveData<Resource<CharacterDetail>> = repository.getCharacterById(id)

}