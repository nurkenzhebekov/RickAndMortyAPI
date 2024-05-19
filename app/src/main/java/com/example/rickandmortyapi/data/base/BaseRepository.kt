package com.example.rickandmortyapi.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmortyapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T): LiveData<Resource<T>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = request()
                emit(Resource.Success(response))
            } catch (io: IOException) {
                emit(Resource.Error(io.localizedMessage?: "Unknown error"))
            }
        }
    }

}