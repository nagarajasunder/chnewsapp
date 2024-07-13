package com.geekydroid.chnewsapp.core.network

import okhttp3.ResponseBody

sealed class NetworkResponse<T> {

    class Success<T>(val data: T, val responseCode: Int) : NetworkResponse<T>()
    class Error<T>(val responseCode: Int,val errorBody: ResponseBody?, val errorMessage: String) : NetworkResponse<T>()
    class Exception<T>(val exception: Throwable) : NetworkResponse<T>()
}