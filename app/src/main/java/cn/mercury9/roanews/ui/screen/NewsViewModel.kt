package cn.mercury9.roanews.ui.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import cn.mercury9.roanews.App
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.data.repo.NewsContentRepo
import cn.mercury9.roanews.data.repo.NewsInfoRepo
import kotlinx.coroutines.launch

sealed interface NewsUiState {
    data class SuccessLoadNewsList(val newsList: List<NewsInfo>): NewsUiState
    data object LoadingNewsList: NewsUiState
    data class ErrorLoadingNewsList(val e: Exception): NewsUiState

    data class SuccessLoadNewsContent(val newsContent: String): NewsUiState
    data object LoadingNewsContent: NewsUiState
    data class ErrorLoadingNewsContent(val e: Exception): NewsUiState
}

class NewsViewModel(
    private val application: App,
    private val newsInfoRepo: NewsInfoRepo,
    private val newsContentRepo: NewsContentRepo
): ViewModel() {
    var newsUiState: NewsUiState by mutableStateOf(NewsUiState.LoadingNewsList)
        private set

    init {
        try {
            loadNewsList()
        } catch (e: Exception) {
            newsUiState = NewsUiState.ErrorLoadingNewsList(e)
        }
    }

    fun getNewsList() {
        viewModelScope.launch {
            newsUiState = NewsUiState.LoadingNewsList
            newsUiState = NewsUiState.SuccessLoadNewsList(application.container.newsInfoList)
        }
    }

    fun loadNewsList() {
        viewModelScope.launch {
            application.container.newsInfoList = newsInfoRepo.getNewsList()
            getNewsList()
        }
    }

    fun getNewsContent(target: String) {
        viewModelScope.launch {
            newsUiState = NewsUiState.LoadingNewsContent
            newsUiState = try {
                val content =
                    application.container.newsContentMap[target]
                        ?: newsContentRepo.getNewsContent(target)
                NewsUiState.SuccessLoadNewsContent(content)
            } catch (e: Exception) {
                NewsUiState.ErrorLoadingNewsContent(e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as App)
                val newsInfoRepo = application.container.newsInfoRepo
                val newsContentRepo = application.container.newsContentRepo
                NewsViewModel(
                    application = application,
                    newsInfoRepo = newsInfoRepo,
                    newsContentRepo = newsContentRepo
                )
            }
        }
    }
}