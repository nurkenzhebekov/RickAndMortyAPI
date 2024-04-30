package com.example.rickandmortyapi.data.api

import com.example.rickandmortyapi.data.model.CharacterList
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    fun getAllCharacters() : Call<CharacterList>
}