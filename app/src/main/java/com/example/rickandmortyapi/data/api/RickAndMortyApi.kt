package com.example.rickandmortyapi.data.api

import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.model.CharacterDetail
import com.example.rickandmortyapi.data.model.CharacterList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character")
    fun getAllCharacters(): Call<CharacterList>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<CharacterDetail>
}