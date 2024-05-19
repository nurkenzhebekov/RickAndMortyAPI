package com.example.rickandmortyapi.di

import com.example.rickandmortyapi.BuildConfig
import com.example.rickandmortyapi.data.api.RickAndMortyApi
import com.example.rickandmortyapi.data.repository.CharacterRepository
import com.example.rickandmortyapi.data.repository.CharactersRepository
import com.example.rickandmortyapi.ui.fragment.character.CharacterViewModel
import com.example.rickandmortyapi.ui.fragment.characters.CharactersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single {
        provideApi(get())
    }

    single {
        provideInterceptor()
    }

    factory {
        CharactersRepository(get())
    }

    factory {
        CharacterRepository(get())
    }

    viewModel {
        CharactersViewModel(get())
    }

    viewModel {
        CharacterViewModel(get())
    }

}

fun provideApi(
    interceptor: HttpLoggingInterceptor
): RickAndMortyApi {
    val client = OkHttpClient.Builder()
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RickAndMortyApi::class.java)
}

fun provideInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }