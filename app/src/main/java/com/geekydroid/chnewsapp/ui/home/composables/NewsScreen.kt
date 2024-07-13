package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {

    Surface(modifier = modifier.fillMaxSize()) {

        Text(
            text = "Today's Headlines",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp
            )
        )
    }
}