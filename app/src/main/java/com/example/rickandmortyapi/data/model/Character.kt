package com.example.rickandmortyapi.data.model

data class Character(
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val location: Location,
    val episode: List<String>
)

data class CharacterList(
    val results : List<Character>
)

data class Location(
    val name: String,
    val url: String
)