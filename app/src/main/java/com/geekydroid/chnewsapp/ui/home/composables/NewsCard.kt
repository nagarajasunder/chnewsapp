package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.LocalTextStyle
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.geekydroid.chnewsapp.R
import com.geekydroid.chnewsapp.ui.home.model.NewsItem


@Composable
fun NewsCard(modifier: Modifier = Modifier) {
    val data by remember {
        mutableStateOf(NewsItem(
            title = "Elon Musk Donates ‘Sizable Amount’ of Money to Pro-Trump Super PAC: Report",
            author = "Charisma Madarang",
            url = "https://www.rollingstone.com/politics/politics-news/elon-musk-donates-trump-super-pac-1235059581/",
            image = "https://www.rollingstone.com/wp-content/uploads/2024/07/elon-musk-trump-donate.jpg?w=1600&h=900&crop=1",
            publishedAt = "2024-07-13T01:38:34Z",
            content = "Despite Elon Muskwriting in March, “I am not donating money to either candidate for US President,” the billionaire has donated to a super political action committee working to elect former president … [+1630 chars]"
        ))
    }
    Card(
        modifier = modifier
            .width(400.dp)
            .fillMaxHeight()
            .padding(16.dp),
        onClick = { /*TODO*/ }) {

        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                modifier = Modifier
                    .width(400.dp)
                    .height(100.dp),
                placeholder = painterResource(id = R.mipmap.ic_launcher),
                model = data.image, contentDescription = null
            )
            Text(
                text = data.title,
                style = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = data.content,
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}


@Preview(device = Devices.TV_720p)
@Composable
fun NewsCardPreview(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NewsCard()
    }
}