package com.example.rickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmortyapi.data.api.RickAndMortyApi
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: RickAndMortyApi
) {
    fun getCharacterById(id: Int): LiveData<Resource<Character>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = api.getCharacterById(id)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        emit(Resource.Success(it))
                    }
                } else {
                    emit(Resource.Error("Character not found"))
                }
            } catch (io: IOException) {
                emit(Resource.Error(io.localizedMessage?: "Unknown error"))
            }
        }
    }
}