package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.geekydroid.chnewsapp.R
import com.geekydroid.chnewsapp.ui.home.model.NewsItem

@Composable
fun NewsContent(modifier: Modifier = Modifier, news: List<NewsItem>) {
    if (news.isEmpty()) {
        EmptyNewsContent()
    } else {
        LazyRow(modifier = modifier) {
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
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.no_results),
            contentDescription = stringResource(id = R.string.content)
        )
        Text(text = stringResource(id = R.string.no_news_found))
    }
}