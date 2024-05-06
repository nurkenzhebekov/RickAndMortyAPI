package com.example.rickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmortyapi.data.api.RickAndMortyApi
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: RickAndMortyApi
) {
    fun getCharacter(): LiveData<Resource<List<Character>>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = api.getAllCharacters()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        emit(Resource.Success(it.results))
                    }
                }
            } catch (io: IOException) {
                emit(Resource.Error(io.localizedMessage?: "Unknown error"))
            }
        }
    }
}