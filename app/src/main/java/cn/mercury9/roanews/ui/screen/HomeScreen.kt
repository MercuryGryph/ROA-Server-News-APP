package cn.mercury9.roanews.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.mercury9.roanews.ui.screen.newslist.LoadingNewsListScreen

@Composable
fun HomeScreen(
    newsUiState: NewsUiState,
    onReloadNewsList: () -> Unit,
    onReloadNewsContent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (newsUiState) {
        is NewsUiState.SuccessLoadNewsList -> TODO()
        NewsUiState.LoadingNewsList ->
            LoadingNewsListScreen(modifier = modifier)
        is NewsUiState.ErrorLoadingNewsList -> TODO()

        is NewsUiState.SuccessLoadNewsContent -> TODO()
        NewsUiState.LoadingNewsContent -> TODO()
        is NewsUiState.ErrorLoadingNewsContent -> TODO()
    }
}
