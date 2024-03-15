package cn.mercury9.roanews.ui.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.mercury9.roanews.ui.screen.newslist.ErrorLoadingNewsListScreen
import cn.mercury9.roanews.ui.screen.newslist.LoadingNewsListScreen
import cn.mercury9.roanews.ui.theme.RoaNewsTheme
import com.king.ultraswiperefresh.UltraSwipeRefresh
import com.king.ultraswiperefresh.rememberUltraSwipeRefreshState

@Composable
fun HomeScreen(
    newsUiState: NewsUiState,
    curlNewsContent: String,
    refreshNewsList: () -> Unit,
    refreshNewsContent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val refreshState = rememberUltraSwipeRefreshState()
    val scrollState = rememberScrollState()

    UltraSwipeRefresh(
        state = refreshState,
        onRefresh = {
            when {
                (newsUiState is NewsUiState.ErrorLoadingNewsList ||
                    newsUiState is NewsUiState.SuccessLoadNewsList
                ) -> refreshNewsList()

                (newsUiState is NewsUiState.ErrorLoadingNewsContent ||
                    newsUiState is NewsUiState.SuccessLoadNewsContent
                ) -> TODO()

                else -> {}
            }
        },
        onLoadMore = {},
        loadMoreEnabled = false,
        refreshEnabled = when (newsUiState) {
            NewsUiState.LoadingNewsContent -> false
            NewsUiState.LoadingNewsList -> false
            else -> true
        }
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
                        .scrollable(scrollState, Orientation.Horizontal)
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
    RoaNewsTheme {
        HomeScreen(
            newsUiState = NewsUiState.ErrorLoadingNewsList(Exception("error")),
            curlNewsContent = "",
            refreshNewsList = {},
            refreshNewsContent = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
