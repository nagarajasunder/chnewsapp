package com.geekydroid.chnewsapp.ui.home.model

import com.geekydroid.chnewsapp.ui.model.UiResult

data class NewsScreenState(
    val isFetching:Boolean,
    val news:UiResult<List<NewsItem>>
) {
    companion object {
        val initialState = NewsScreenState(isFetching = true, news = UiResult.Loading(message = ""))
    }
}