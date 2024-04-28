package com.example.rickandmortyapi.api

import com.example.rickandmortyapi.model.CharacterList
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("api/character")
    fun getAllCharacters() : Call<CharacterList>
}