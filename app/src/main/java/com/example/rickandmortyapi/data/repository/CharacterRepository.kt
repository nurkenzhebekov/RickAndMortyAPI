package com.example.rickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmortyapi.data.api.RickAndMortyApi
import com.example.rickandmortyapi.data.base.BaseRepository
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class CharacterRepository (
    private val api: RickAndMortyApi
): BaseRepository() {
    fun getCharacterById(id: Int) = doRequest {
        api.getCharacterById(id)
    }
}