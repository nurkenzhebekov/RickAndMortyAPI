package com.example.rickandmortyapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapi.api.Repository
import com.example.rickandmortyapi.model.CharacterList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getCharacter() : LiveData<CharacterList> {
        val liveData = repository.getCharacter()
        liveData.observeForever { characterList ->
            Log.d("CharacterViewModel", "Received character list with ${characterList.results.size} characters")
        }
        return liveData
    }
}