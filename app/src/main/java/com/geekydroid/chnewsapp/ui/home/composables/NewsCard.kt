package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Glow
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
            .width(920.dp)
            .padding(start = 24.dp, top = 8.dp),
        scale = CardDefaults.scale(scale = 0.95f, focusedScale = 1f),
        onClick = {},
        border = CardDefaults.border(border = Border.None),
        glow = CardDefaults.glow(glow = Glow(MaterialTheme.colorScheme.primary,8.dp))

    ) {

        Row {
            if (news.image.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier
                        .width(320.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    model = news.image, contentDescription = stringResource(id = R.string.banner_image),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = stringResource(id = R.string.banner_image),
                    modifier = Modifier
                        .width(400.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
            }
            Column {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = news.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
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
                            textAlign = TextAlign.Right,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        text = Utils.parseDateString(news.publishedAt),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(end = 8.dp, bottom = 4.dp),
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Right
                    )
                }
                if (news.description.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(
                            bottom = 8.dp,
                            start = 12.dp,
                            end = 12.dp
                        ),
                        text = news.description,
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (news.content.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(
                            bottom = 16.dp,
                            start = 12.dp,
                            end = 12.dp
                        ),
                        overflow = TextOverflow.Ellipsis,
                        text = news.content,
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

