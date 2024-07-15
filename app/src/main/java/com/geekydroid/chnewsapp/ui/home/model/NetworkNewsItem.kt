package com.geekydroid.chnewsapp.ui.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkNewsItem(
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val image: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("content")
    val content: String?
)

fun NetworkNewsItem.toNewsItem(): NewsItem = NewsItem(
    source = source?.name ?: "",
    title = title ?: "",
    author = author ?: "",
    url = url ?: "",
    image = image ?: "",
    publishedAt = publishedAt ?: "",
    description = description?:"",
    content = content ?: ""
)