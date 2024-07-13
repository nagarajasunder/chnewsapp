package com.geekydroid.chnewsapp.core.network

import retrofit2.Response


suspend fun <T> makeNetworkCall(
    block: suspend () -> Response<T>
): NetworkResponse<T> {
    try {
        val result = block()
        return if (result.isSuccessful && result.body() != null) {
            NetworkResponse.Success(data = result.body()!!, responseCode = result.code())
        } else {
            NetworkResponse.Error(
                responseCode = result.code(),
                errorBody = result.errorBody(),
                errorMessage = ""
            )
        }
    } catch (e: Exception) {
        return NetworkResponse.Exception(exception = e)
    }
}