package cn.mercury9.roanews.ui.screen.newslist

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.ui.screen.NewsUiState
import cn.mercury9.roanews.ui.theme.RoaNewsTheme

@Composable
fun NewsListScreen(
    newsUiState: NewsUiState.SuccessLoadNewsList,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(newsUiState.newsList) { newsInfo ->
            NewsInfoRow(
                newsInfo = newsInfo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun NewsInfoRow(
    newsInfo: NewsInfo,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 6.dp,
        tonalElevation = 6.dp,
        modifier = modifier
    ) {
        Text(
            text = newsInfo.title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewNewsInfoRow() {
    RoaNewsTheme {
        NewsInfoRow(
            newsInfo = NewsInfo("News 1", ""),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    locale = "zh"
)
@Composable
fun PreviewNewsListScreen() {
    val newsList: MutableList<NewsInfo> = mutableListOf()
    for (i in 1..20) {
        newsList += NewsInfo("News $i", "")
    }
    RoaNewsTheme {
        NewsListScreen(
            newsUiState = NewsUiState.SuccessLoadNewsList(newsList),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
