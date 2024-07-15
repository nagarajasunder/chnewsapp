package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewsLoadingContent(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(5) {
            ShimmerAnimationCard(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .height(200.dp))
        }
    }
}