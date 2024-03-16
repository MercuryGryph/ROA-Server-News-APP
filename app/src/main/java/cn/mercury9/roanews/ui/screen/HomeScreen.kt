package cn.mercury9.roanews.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.ui.screen.newslist.ErrorLoadingNewsListScreen
import cn.mercury9.roanews.ui.screen.newslist.LoadingNewsListScreen
import cn.mercury9.roanews.ui.screen.newslist.NewsListScreen
import cn.mercury9.roanews.ui.theme.RoaNewsTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    newsUiState: NewsUiState,
    curlNewsContent: String,
    refreshNewsList: () -> Unit,
    refreshNewsContent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    val scrollState = rememberScrollState()

    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            when {
                (newsUiState is NewsUiState.SuccessLoadNewsList ||
                newsUiState is NewsUiState.ErrorLoadingNewsList)
                        -> refreshNewsList()
                (newsUiState is NewsUiState.SuccessLoadNewsContent ||
                newsUiState is NewsUiState.ErrorLoadingNewsContent)
                        -> refreshNewsContent(curlNewsContent)
                else -> {}
            }
        }
    ) {
        when (newsUiState) {
            is NewsUiState.SuccessLoadNewsList ->
                NewsListScreen(
                    newsUiState = newsUiState,
                    modifier = modifier
                )
            NewsUiState.LoadingNewsList ->
                LoadingNewsListScreen(
                    modifier = modifier
                )
            is NewsUiState.ErrorLoadingNewsList ->
                ErrorLoadingNewsListScreen(
                    newsUiState = newsUiState,
                    modifier = modifier
                        .verticalScroll(scrollState)
                )

            is NewsUiState.SuccessLoadNewsContent -> TODO()
            NewsUiState.LoadingNewsContent -> TODO()
            is NewsUiState.ErrorLoadingNewsContent -> TODO()
        }
        
    }

}

@Preview
@Composable
fun PreviewHomeScreen() {

    val newsList: MutableList<NewsInfo> = mutableListOf()
    for (i in 1..20) {
        newsList += NewsInfo("News $i", "")
    }

    RoaNewsTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                newsUiState = NewsUiState.SuccessLoadNewsList(newsList),
                curlNewsContent = "",
                refreshNewsList = {},
                refreshNewsContent = {},
                modifier = Modifier
                    .fillMaxSize()
            )

        }
    }
}
