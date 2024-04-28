package com.example.rickandmortyapi.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapi.model.CharacterList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RickAndMortyApi
) {

    fun getCharacter(): MutableLiveData<CharacterList> {
        val liveData = MutableLiveData<CharacterList>()
        api.getAllCharacters().enqueue(object : Callback<CharacterList> {
            override fun onResponse(p0: Call<CharacterList>, response: Response<CharacterList>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveData.postValue(it)
                        Log.d("Repository", "Received: ${it.results.size} characters")
                    }
                }
            }

            override fun onFailure(p0: Call<CharacterList>, error: Throwable) {
                Log.e("loge", "onFailure: ${error.message}")
            }

        })
        return liveData
    }
}