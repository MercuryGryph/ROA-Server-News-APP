package cn.mercury9.roanews.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cn.mercury9.roanews.ui.screen.newslist.ErrorLoadingNewsListScreen
import cn.mercury9.roanews.ui.screen.newslist.LoadingNewsListScreen
import com.king.ultraswiperefresh.UltraSwipeRefresh
import com.king.ultraswiperefresh.rememberUltraSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    newsUiState: NewsUiState,
    onRefreshNewsList: () -> Unit,
    onRefreshNewsContent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val refreshState = rememberUltraSwipeRefreshState()
    LaunchedEffect(refreshState.isRefreshing) {
        if (refreshState.isRefreshing) {
            delay(1000)
        }
    }
    UltraSwipeRefresh(
        state = refreshState,
        onRefresh = {
            when (newsUiState) {
                NewsUiState.LoadingNewsList -> TODO()
                is NewsUiState.ErrorLoadingNewsList -> TODO()
                is NewsUiState.SuccessLoadNewsList -> TODO()
                NewsUiState.LoadingNewsContent -> TODO()
                is NewsUiState.SuccessLoadNewsContent -> TODO()
                is NewsUiState.ErrorLoadingNewsContent -> TODO()
            }
        },
        onLoadMore = {},
        loadMoreEnabled = false
    ) {
        when (newsUiState) {
            is NewsUiState.SuccessLoadNewsList -> TODO()
            NewsUiState.LoadingNewsList ->
                LoadingNewsListScreen(
                    modifier = modifier
                )
            is NewsUiState.ErrorLoadingNewsList ->
                ErrorLoadingNewsListScreen(
                    newsUiState = newsUiState,
                    modifier = modifier
                )

            is NewsUiState.SuccessLoadNewsContent -> TODO()
            NewsUiState.LoadingNewsContent -> TODO()
            is NewsUiState.ErrorLoadingNewsContent -> TODO()
        }
    }
}
