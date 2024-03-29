package cn.mercury9.roanews.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.launch

sealed interface NewsUiState {
    data class SuccessLoadNewsList(val newsList: List<NewsInfo>): NewsUiState
    data object LoadingNewsList: NewsUiState
    data class ErrorLoadingNewsList(val e: Exception): NewsUiState
}

sealed interface NewsContentState {
    data class SuccessLoadNewsContent(val newsContent: String): NewsContentState
    data object LoadingNewsContent: NewsContentState
    data class ErrorLoadingNewsContent(val e: Exception): NewsContentState
}

class NewsViewModel(
    private val application: App,
    private val newsInfoRepo: NewsInfoRepo,
    private val newsContentRepo: NewsContentRepo
): ViewModel() {
    var newsUiState: NewsUiState
        by mutableStateOf(NewsUiState.LoadingNewsList)
        private set

    var newsContentState: NewsContentState
        by mutableStateOf(NewsContentState.LoadingNewsContent)
        private set

    init {
        newsUiState = NewsUiState.LoadingNewsList
        try {
            loadNewsList()
        } catch (e: Exception) {
            newsUiState = NewsUiState.ErrorLoadingNewsList(e)
        }
    }

    fun loadNewsList(refreshState: SwipeRefreshState? = null) {
        viewModelScope.launch {
            Log.i("load_data", "Loading News List")
            try {
                newsUiState = NewsUiState.LoadingNewsList
                application.container.newsInfoList = newsInfoRepo.getNewsList()
                newsUiState = NewsUiState.SuccessLoadNewsList(application.container.newsInfoList)
            } catch (e: Exception) {
                newsUiState = NewsUiState.ErrorLoadingNewsList(e)
            } finally {
                if (refreshState != null) {
                    refreshState.isRefreshing = false
                }
            }
        }
    }

    fun setNewsContentTarget(target: NewsInfo) {
        application.curlNewsContent = target
    }

    fun getNewsContentTarget(): NewsInfo {
        return application.curlNewsContent
    }

    fun getNewsContent(target: String) {
        viewModelScope.launch {
            newsContentState = NewsContentState.LoadingNewsContent
            newsContentState = try {
                val content =
                    application.container.newsContentMap[target]
                    ?: run {
                        val r = newsContentRepo.getNewsContent(target)
                        application.container.newsContentMap[target] = r
                        r
                    }
                NewsContentState.SuccessLoadNewsContent(content)
            } catch (e: Exception) {
                NewsContentState.ErrorLoadingNewsContent(e)
            }
        }
    }

    fun loadNewsContent(refreshState: SwipeRefreshState? = null, target: String) {
        viewModelScope.launch {
            newsContentState = NewsContentState.LoadingNewsContent
            newsContentState = try {
                val content = newsContentRepo.getNewsContent(target)
                application.container.newsContentMap[target] = content
                NewsContentState.SuccessLoadNewsContent(content)
            } catch (e: Exception) {
                NewsContentState.ErrorLoadingNewsContent(e)
            } finally {
                if (refreshState != null) {
                    refreshState.isRefreshing = false
                }
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