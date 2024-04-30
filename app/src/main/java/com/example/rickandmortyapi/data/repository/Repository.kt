package com.example.rickandmortyapi.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapi.data.api.RickAndMortyApi
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.model.CharacterList
import com.example.rickandmortyapi.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RickAndMortyApi
) {

    fun getCharacter(): MutableLiveData<Resource<List<Character>>> {
        val liveData = MutableLiveData<Resource<List<Character>>>()
        liveData.postValue(Resource.Loading())
        api.getAllCharacters().enqueue(object : Callback<CharacterList> {
            override fun onResponse(p0: Call<CharacterList>, response: Response<CharacterList>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveData.postValue(
                            Resource.Success(it.results)
                        )
                    }
                }
            }

            override fun onFailure(p0: Call<CharacterList>, t: Throwable) {
                liveData.postValue(Resource.Error(t.localizedMessage?:"Unknown exception"))
            }

        })
        return liveData
    }
}