package com.example.rickandmortyapi.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmortyapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> Response<T>): LiveData<Resource<T>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = request()
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        emit(Resource.Success(result))
                    } ?: emit(Resource.Error("Response body is null"))
                } else {
                    emit(Resource.Error("Error code: ${response.code()} - ${response.message()}"))
                }
            } catch (io: IOException) {
                emit(Resource.Error(io.localizedMessage ?: "Network error"))
            } catch (exception: Exception) {
                emit(Resource.Error(exception.localizedMessage ?: "Unknown error"))
            }
        }
    }

    protected fun <T, R> doRequestList(request: suspend () -> Response<T>
                                       , transform: (T) -> List<R>): LiveData<Resource<List<R>>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = request()
                if (response.isSuccessful) {
                    response.body()?.let {result ->
                        emit(Resource.Success(transform(result)))
                    } ?: emit(Resource.Error("Response body is null"))
                } else {
                    emit(Resource.Error("Error code: ${response.code()} - ${response.message()}"))
                }
            } catch (io: IOException) {
                emit(Resource.Error(io.localizedMessage ?: "Network error"))
            } catch (exception: Exception) {
                emit(Resource.Error(exception.localizedMessage ?: "Unknown error"))
            }
        }
    }
}