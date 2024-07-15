package com.geekydroid.chnewsapp.ui.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val total: Int,
    @SerializedName("articles")
    val articles: List<NetworkNewsItem>
)