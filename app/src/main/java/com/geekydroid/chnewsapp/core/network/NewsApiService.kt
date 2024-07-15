package com.geekydroid.chnewsapp.core.network

import com.geekydroid.chnewsapp.BuildConfig
import com.geekydroid.chnewsapp.ui.home.model.NetworkNewsItem
import com.geekydroid.chnewsapp.ui.home.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category:String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<NewsResponse>
}