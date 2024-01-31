package com.example.mycalck.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycalck.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

abstract class BaseRepository {

    protected fun <T> doRequest(
        request: suspend () -> Resource<T>
    ): LiveData<Resource<T>> {
        val result = MutableLiveData<Resource<T>>()
        result.value = Resource.Loading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = request()
                result.postValue(response)
            } catch (e: Exception) {
                result.postValue(Resource.Error(e.message ?: "Unknown error"))
            }
        }

        return result
    }
}


