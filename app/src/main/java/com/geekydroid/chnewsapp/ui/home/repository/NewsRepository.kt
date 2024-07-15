package com.geekydroid.chnewsapp.ui.home.repository

import com.geekydroid.chnewsapp.core.network.NetworkResponse
import com.geekydroid.chnewsapp.core.network.NewsApiService
import com.geekydroid.chnewsapp.core.network.makeNetworkCall
import com.geekydroid.chnewsapp.ui.home.model.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepository @Inject constructor(private val newsApiService: NewsApiService) {

    suspend fun getNews(country:String,category:String): NetworkResponse<NewsResponse> {
        return makeNetworkCall { newsApiService.getNews(country = country,category = category) }
    }

}