package cn.mercury9.roanews.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.ui.screen.newslist.ErrorLoadingNewsListScreen
import cn.mercury9.roanews.ui.screen.newslist.LoadingNewsListScreen
import cn.mercury9.roanews.ui.screen.newslist.NewsListScreen
import cn.mercury9.roanews.ui.theme.RoaNewsTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    newsUiState: NewsUiState,
    refreshNewsList: (SwipeRefreshState) -> Unit,
    onClickNews: (NewsInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    val scrollState = rememberScrollState()

    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            refreshState.isRefreshing = true
            refreshNewsList(refreshState)
        }
    ) {
        when (newsUiState) {
            is NewsUiState.SuccessLoadNewsList ->
                NewsListScreen(
                    newsUiState = newsUiState,
                    onClickNews = {target: NewsInfo -> onClickNews(target)},
                    modifier = modifier
                )
            NewsUiState.LoadingNewsList ->
                LoadingNewsListScreen(
                    modifier = modifier
                        .verticalScroll(scrollState)
                )
            is NewsUiState.ErrorLoadingNewsList ->
                ErrorLoadingNewsListScreen(
                    newsUiState = newsUiState,
                    modifier = modifier
                        .verticalScroll(scrollState)
                )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {

    val newsList: MutableList<NewsInfo> = mutableListOf()
    for (i in 1..10) {
        newsList += NewsInfo("News $i", "")
    }

    val newsUiStateSuccess = NewsUiState.SuccessLoadNewsList(newsList)
    val newsUiStateError = NewsUiState.ErrorLoadingNewsList(Exception("error"))

    var newsUiState: NewsUiState by remember {
        mutableStateOf(newsUiStateError)
    }

    RoaNewsTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                newsUiState = newsUiState,
                refreshNewsList = {
                    newsUiState = newsUiStateSuccess
                },
                onClickNews = {},
                modifier = Modifier
                    .fillMaxSize()
            )

        }
    }
}
