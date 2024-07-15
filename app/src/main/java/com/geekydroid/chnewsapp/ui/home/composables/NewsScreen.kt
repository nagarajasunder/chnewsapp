package com.geekydroid.chnewsapp.ui.home.composables

import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.geekydroid.chnewsapp.ui.home.viewmodel.HomeScreenViewModel
import com.geekydroid.chnewsapp.ui.model.UiResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {

    val viewModel: HomeScreenViewModel = viewModel()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    var longPressJob by remember {
        mutableStateOf<Job?>(null)
    }

    Surface(modifier = modifier
        .fillMaxSize()
        .onKeyEvent { keyEvent ->
            when(keyEvent.key) {
                Key.DirectionDown -> {
                    if (keyEvent.nativeKeyEvent.action == ACTION_DOWN) {
                        if(longPressJob == null || longPressJob?.isActive == false) {
                            longPressJob = coroutineScope.launch {
                                delay(1000)
                                viewModel.fetchHeadlines()
                            }
                            false
                        } else {
                            false
                        }
                    } else if (keyEvent.nativeKeyEvent.action == ACTION_UP) {
                        longPressJob?.cancel()
                        longPressJob = null
                        false
                    } else {
                        false
                    }
                }
                else -> false
            }
        }) {

        Column(modifier = Modifier.padding(12.dp)) {
            AnimatedVisibility(visible = screenState.isFetching) {

            }
            Text(
                text = "Today's Headlines",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp
                )
            )
            CountryChipRow(
                selectedCountry = screenState.selectedCountry,
                onCountryChange = viewModel::onCountryChange
            )
            CategoryChipRow(
                selectedCategory = screenState.selectedCategory,
                onCategoryChange = viewModel::onCategoryChange
            )
            when (screenState.news) {
                is UiResult.Loading -> {
                    NewsLoadingContent()
                }

                is UiResult.Error -> {
                    NewsErrorContent()
                }

                is UiResult.Success -> {
                    if (screenState.news.data != null) {
                        NewsContent(news = screenState.news.data!!)
                    }
                }
            }
        }
    }
}