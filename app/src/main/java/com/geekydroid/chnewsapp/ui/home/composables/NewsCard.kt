package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Card
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.geekydroid.chnewsapp.R
import com.geekydroid.chnewsapp.core.Utils
import com.geekydroid.chnewsapp.ui.home.model.NewsItem


@Composable
fun NewsCard(modifier: Modifier = Modifier, news: NewsItem) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {}) {

        Column {
            if (news.image.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    model = news.image, contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                modifier = Modifier.padding(12.dp),
                text = news.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Left
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (news.source.isNotEmpty()) {
                    Text(
                        text = "source: ${news.source}",
                        modifier = Modifier.padding(end = 4.dp, bottom = 4.dp),
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Right
                    )
                }
                Text(
                    text = Utils.parseDateString(news.publishedAt),
                    modifier = Modifier.padding(end = 8.dp, bottom = 4.dp),
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Right
                )
            }
            if (news.content.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(
                        bottom = 16.dp,
                        start = 12.dp,
                        end = 12.dp
                    ),
                    text = news.content,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
                )
            }
        }
    }
}

