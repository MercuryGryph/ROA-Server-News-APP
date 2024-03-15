package cn.mercury9.roanews.ui.screen.newslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cn.mercury9.roanews.R
import cn.mercury9.roanews.ui.screen.NewsUiState
import cn.mercury9.roanews.ui.theme.RoaNewsTheme

@Composable
fun ErrorLoadingNewsListScreen(
    newsUiState: NewsUiState.ErrorLoadingNewsList,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(
                text = stringResource(id = R.string.fail_load_news_list),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .wrapContentSize()
            )
            Text(
                text = newsUiState.e.toString()
                    .replace(": ", ":\n\t")
                    .replace(";", ";\n"),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .wrapContentSize()
            )
        }
    }
}

@Preview(
    locale = ""
)
@Composable
fun PreviewErrorLoadingNewsListScreen() {
    RoaNewsTheme {
        ErrorLoadingNewsListScreen(
            newsUiState = NewsUiState.ErrorLoadingNewsList(Exception("error")),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
