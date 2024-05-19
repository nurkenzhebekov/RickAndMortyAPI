package com.example.rickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmortyapi.data.api.RickAndMortyApi
import com.example.rickandmortyapi.data.base.BaseRepository
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.model.CharacterList
import com.example.rickandmortyapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException

class CharactersRepository (
    private val api: RickAndMortyApi
): BaseRepository() {
    fun getCharacter(): LiveData<Resource<List<Character>>> = doRequestList(
        request = {api.getAllCharacters()},
        transform = {it.results}
    )

        /*liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = api.getAllCharacters()
            if (response.isSuccessful) {
                val result = response.body()?.results
                if (result != null) {
                    emit(Resource.Success(result))
                } else {
                    emit(Resource.Error("Response body is null"))
                }
            } else {
                emit(Resource.Error("Error code: ${response.code()} - ${response.message()}"))
            }
        } catch (io: IOException) {
            emit(Resource.Error(io.localizedMessage ?: "Network error"))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage ?: "Unknown error"))
        }
    }*/
}