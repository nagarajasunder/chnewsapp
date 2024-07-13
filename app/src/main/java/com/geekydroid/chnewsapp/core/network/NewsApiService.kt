package com.geekydroid.chnewsapp.core.network

import com.geekydroid.chnewsapp.ui.home.model.NetworkNewsItem
import retrofit2.Response
import retrofit2.http.GET

interface NewsApiService {

    @GET("")
    suspend fun getNews():Response<List<NetworkNewsItem>>
}