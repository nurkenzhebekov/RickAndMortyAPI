package com.example.rickandmortyapi.di

import com.example.rickandmortyapi.ui.fragment.character.CharacterViewModel
import com.example.rickandmortyapi.ui.fragment.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { CharacterViewModel(get()) }
}