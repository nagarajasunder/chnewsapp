package com.geekydroid.chnewsapp.ui.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkNewsItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val image: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("content")
    val content: String
)

fun NetworkNewsItem.toNewsItem(): NewsItem = NewsItem(
    title = title,
    author = author,
    url = url,
    image = image,
    publishedAt = publishedAt,
    content = content
)