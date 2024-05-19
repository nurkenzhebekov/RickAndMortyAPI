package com.example.rickandmortyapi.di

import com.example.rickandmortyapi.data.repository.CharacterRepository
import com.example.rickandmortyapi.data.repository.CharactersRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { CharactersRepository(get()) }
    factory { CharacterRepository(get()) }
}