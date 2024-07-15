package com.geekydroid.chnewsapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekydroid.chnewsapp.core.Constants.countryMap
import com.geekydroid.chnewsapp.core.network.NetworkResponse
import com.geekydroid.chnewsapp.ui.home.model.NewsScreenState
import com.geekydroid.chnewsapp.ui.home.model.toNewsItem
import com.geekydroid.chnewsapp.ui.home.repository.NewsRepository
import com.geekydroid.chnewsapp.ui.home.screenactions.NewsScreenActions
import com.geekydroid.chnewsapp.ui.home.screenevents.NewsScreenEvents
import com.geekydroid.chnewsapp.ui.model.UiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel(), NewsScreenActions {

    private val _screenState: MutableStateFlow<NewsScreenState> =
        MutableStateFlow(NewsScreenState.initialState)
    val screenState: StateFlow<NewsScreenState> = _screenState
    private val eventChannel: Channel<NewsScreenEvents> = Channel()
    val events = eventChannel.receiveAsFlow()

    init {
        fetchHeadlines()
    }


    fun fetchHeadlines() {
        viewModelScope.launch {
            updateScreenState(
                _screenState.value.copy(
                    isFetching = true,
                    news = UiResult.Loading(messageText = "Fetching the latest news...")
                )
            )
            when (val result = newsRepository.getNews(
                _screenState.value.selectedCountryCode,
                _screenState.value.selectedCategory.lowercase(Locale.getDefault())
            )) {
                is NetworkResponse.Error, is NetworkResponse.Exception -> {
                    updateScreenState(
                        _screenState.value.copy(
                            isFetching = false,
                            news = UiResult.Error(errorMessage = "Unable to fetch the news. Please try again!")
                        )
                    )
                }

                is NetworkResponse.Success -> {
                    if (result.data.status == "ok" && result.data.total > 0) {
                        val newsList = result.data.articles.map { it.toNewsItem() }
                        updateScreenState(
                            _screenState.value.copy(
                                isFetching = false, news = UiResult.Success(successData = newsList)
                            )
                        )
                    } else {
                        updateScreenState(
                            _screenState.value.copy(
                                isFetching = false,
                                news = UiResult.Success(successData = listOf())
                            )
                        )
                    }
                }
            }
        }
    }


    private fun updateScreenState(newState: NewsScreenState): NewsScreenState {
        return _screenState.updateAndGet { newState }
    }


    override fun onCountryChange(country: String) {
        if (countryMap.containsKey(country)) {
            updateScreenState(
                _screenState.value.copy(
                    selectedCountry = country,
                    selectedCountryCode = countryMap[country]!!
                )
            )
            fetchHeadlines()
        }
    }

    override fun onCategoryChange(category: String) {
        updateScreenState(_screenState.value.copy(selectedCategory = category))
        fetchHeadlines()
    }

}