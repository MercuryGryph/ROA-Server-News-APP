package cn.mercury9.roanews.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.mercury9.roanews.R
import cn.mercury9.roanews.data.NewsViewModel
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.ui.screen.NewsScreen
import cn.mercury9.roanews.ui.theme.RoaNewsTheme
import com.google.accompanist.swiperefresh.SwipeRefreshState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RoaNewsContentPage(
    refreshState: SwipeRefreshState,
    navController: NavController,
    newsViewModel: NewsViewModel
) {
    val newsInfo: NewsInfo = newsViewModel.getNewsContentTarget()
    newsViewModel.getNewsContent(newsInfo.href)
    Scaffold(
        modifier = Modifier,
        topBar = {
            RoaNewsContentTopBar(
                title = newsInfo.title,
                onClickNavIcon = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        NewsScreen(
            target = newsInfo,
            newsContentState = newsViewModel.newsContentState,
            refreshState = refreshState,
            refreshNewsContent = { refreshState: SwipeRefreshState ->
                Log.i(null, "Refresh News List")
                newsViewModel.loadNewsContent(refreshState, newsInfo.href)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 65.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoaNewsContentTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onClickNavIcon: () -> Unit = {}
) {
    TopAppBar(
        colors = topAppBarColors(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    onClickNavIcon()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = stringResource(id = R.string.back),
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .wrapContentSize()
            )
        },
        modifier = modifier
            .height(65.dp)
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewTopBar() {
    RoaNewsTheme {
        RoaNewsContentTopBar(
            title = "Roa News 1"
        )
    }
}
