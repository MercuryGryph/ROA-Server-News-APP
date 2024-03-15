package cn.mercury9.roanews.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.mercury9.roanews.R
import cn.mercury9.roanews.ui.screen.HomeScreen
import cn.mercury9.roanews.ui.screen.NewsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoaNewsApp() {
    val scoreBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scoreBehavior.nestedScrollConnection),
        topBar = {
            RoaNewsTopBar(scrollBehavior = scoreBehavior)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val newsViewModel: NewsViewModel = viewModel(
                factory = NewsViewModel.Factory
            )
            HomeScreen(
                newsUiState = newsViewModel.newsUiState,
                curlNewsContent = "",
                refreshNewsList = { newsViewModel.loadNewsList() },
                refreshNewsContent = { target: String -> newsViewModel.getNewsContent(target) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoaNewsTopBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}
