package cn.mercury9.roanews.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.mercury9.roanews.R
import cn.mercury9.roanews.RouteConfig
import cn.mercury9.roanews.data.NewsViewModel
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.ui.screen.HomeScreen
import com.google.accompanist.swiperefresh.SwipeRefreshState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoaNewsListPage(
    navController: NavController,
    newsViewModel: NewsViewModel
) {
    val scoreBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scoreBehavior.nestedScrollConnection),
        topBar = {
            RoaNewsTopBar(scrollBehavior = null)
        }
    ) {
        HomeScreen(
            newsUiState = newsViewModel.newsUiState,
            refreshNewsList = { refreshState: SwipeRefreshState ->
                Log.i(null, "Refresh News List")
                newsViewModel.loadNewsList(refreshState)
            },
            onClickNews = {target: NewsInfo ->
                newsViewModel.setNewsContentTarget(target)
                navController.navigate(RouteConfig.ROUTE_NEWS_CONTENT_PAGE)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
        )
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoaNewsTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.onPrimary
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .wrapContentSize()
            )
        }
    )
}
