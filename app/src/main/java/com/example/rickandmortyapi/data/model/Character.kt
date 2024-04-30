package com.example.rickandmortyapi.data.model

data class Character(
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String
)

data class CharacterList (
    val results : ArrayList<Character>
)