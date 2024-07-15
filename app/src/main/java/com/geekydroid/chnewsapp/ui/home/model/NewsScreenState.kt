package com.geekydroid.chnewsapp.ui.home.model

import com.geekydroid.chnewsapp.ui.model.UiResult
import com.geekydroid.chnewsapp.ui.model.UiText

data class NewsScreenState(
    val isFetching: Boolean,
    val news: UiResult<List<NewsItem>>,
    val selectedCountry: String,
    val selectedCountryCode:String,
    val selectedCategory:String
) {
    companion object {
        val initialState = NewsScreenState(
            isFetching = true,
            news = UiResult.Loading(messageText = ""),
            selectedCountry = "India",
            selectedCountryCode = "in",
            selectedCategory = "Business"
        )
    }
}