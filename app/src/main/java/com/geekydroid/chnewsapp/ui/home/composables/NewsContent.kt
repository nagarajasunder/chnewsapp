package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.tv.material3.Text
import com.geekydroid.chnewsapp.ui.home.model.NewsItem

@Composable
fun NewsContent(modifier: Modifier = Modifier, news: List<NewsItem>) {
    if (news.isEmpty()) {
        EmptyNewsContent()
    } else {
        LazyColumn(modifier = modifier) {
            items(news.size, key = { news[it].title }) { index ->
                NewsCard(news = news[index])
            }
        }
    }
}

@Composable
fun EmptyNewsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "No news found!!")
    }
}