package com.geekydroid.chnewsapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekydroid.chnewsapp.core.network.NetworkResponse
import com.geekydroid.chnewsapp.ui.home.model.NewsScreenState
import com.geekydroid.chnewsapp.ui.home.model.toNewsItem
import com.geekydroid.chnewsapp.ui.home.repository.NewsRepository
import com.geekydroid.chnewsapp.ui.home.screenevents.NewsScreenEvents
import com.geekydroid.chnewsapp.ui.model.UiResult
import com.geekydroid.chnewsapp.ui.model.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {

    private val _screenState: MutableStateFlow<NewsScreenState> =
        MutableStateFlow(NewsScreenState.initialState)
    val screenState: StateFlow<NewsScreenState> = _screenState
    private val eventChannel: Channel<NewsScreenEvents> = Channel()
    val events = eventChannel.receiveAsFlow()

    fun fetchHeadlines() {
        viewModelScope.launch {
            updateScreenState(
                _screenState.value.copy(
                    isFetching = true,
                    news = UiResult.Loading(message = UiText.DynamicText("Fetching the latest news..."))
                )

            )
            when (val result = newsRepository.getNews()) {
                is NetworkResponse.Error, is NetworkResponse.Exception -> {
                    updateScreenState(
                        _screenState.value.copy(
                            isFetching = false,
                            news = UiResult.Error(message = UiText.DynamicText("Unable to fetch the news. Please try again!"))
                        )
                    )
                }

                is NetworkResponse.Success -> {
                    val newsList = result.data.map { it.toNewsItem() }
                    updateScreenState(
                        _screenState.value.copy(
                            isFetching = false,
                            news = UiResult.Success(data = newsList)
                        )
                    )
                }
            }
        }
    }


    private fun updateScreenState(newState: NewsScreenState): NewsScreenState {
        return _screenState.updateAndGet { newState }
    }

}