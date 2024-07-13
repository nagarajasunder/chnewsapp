package com.geekydroid.chnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.geekydroid.chnewsapp.ui.home.composables.NewsScreen
import com.geekydroid.chnewsapp.ui.theme.ChnewsappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChnewsappTheme {

                NewsScreen()
            }
        }
    }
}
