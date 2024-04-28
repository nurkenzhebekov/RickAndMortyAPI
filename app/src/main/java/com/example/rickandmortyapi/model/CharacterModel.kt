package com.example.rickandmortyapi.model

data class CharacterModel(
    val image: String,
    val name: String,
    val status: String,
    val species: String
)

data class CharacterList (
    val results : ArrayList<CharacterModel>
)