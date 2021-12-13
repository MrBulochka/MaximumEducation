package com.bulochka.maximum_education.data.remote

import com.bulochka.maximum_education.data.model.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CustomNetworkCall {
    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                var result = apiCall.invoke()
                ResultWrapper.Success(result)
            } catch (throwable: Exception) {
                ResultWrapper.GenericError(throwable)
            }
        }
    }
}