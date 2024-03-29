package cn.mercury9.roanews

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.mercury9.roanews.data.NewsViewModel
import cn.mercury9.roanews.ui.RoaNewsContentPage
import cn.mercury9.roanews.ui.RoaNewsListPage
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

object RouteConfig {
    const val ROUTE_NEWS_LIST_PAGE = "NewsListPage"
    const val ROUTE_NEWS_CONTENT_PAGE = "NewsContentPage"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val newsViewModel: NewsViewModel = viewModel(
        factory = NewsViewModel.Factory
    )
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    NavHost(
        navController = navController,
        startDestination = RouteConfig.ROUTE_NEWS_LIST_PAGE
    ) {
        composable(RouteConfig.ROUTE_NEWS_LIST_PAGE) {
            RoaNewsListPage(
                refreshState = refreshState,
                navController = navController,
                newsViewModel = newsViewModel
            )
        }

        composable(RouteConfig.ROUTE_NEWS_CONTENT_PAGE) {
            RoaNewsContentPage(
                refreshState = refreshState,
                navController = navController,
                newsViewModel = newsViewModel
            )
        }
    }
}
