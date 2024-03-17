package cn.mercury9.roanews.ui.screen

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.mercury9.roanews.data.NewsContentState
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.ui.screen.newscontent.ErrorLoadingNewsContentScreen
import cn.mercury9.roanews.ui.screen.newscontent.LoadingNewsContentScreen
import cn.mercury9.roanews.ui.screen.newscontent.NewsContentScreen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsScreen(
    target: NewsInfo,
    newsContentState: NewsContentState,
    refreshNewsContent: (SwipeRefreshState) -> Unit,
    modifier: Modifier = Modifier
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    val scrollState = rememberScrollState()

    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            refreshState.isRefreshing = true
            refreshNewsContent(refreshState)
        }
    ) {
        when (newsContentState) {
            is NewsContentState.ErrorLoadingNewsContent ->
                ErrorLoadingNewsContentScreen(
                    newsContentState = newsContentState,
                    target = target,
                    modifier = modifier
                        .verticalScroll(scrollState)
                )
            NewsContentState.LoadingNewsContent ->
                LoadingNewsContentScreen(
                    target = target,
                    modifier = modifier
                        .verticalScroll(scrollState)
                )
            is NewsContentState.SuccessLoadNewsContent ->
                NewsContentScreen(
                    newsContentState = newsContentState,
                    modifier = modifier
                        .verticalScroll(scrollState)
                )
        }
    }
}
