package cn.mercury9.roanews.ui.screen.newslist

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cn.mercury9.roanews.R

@Composable
fun LoadingNewsListScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.news_list_screen_loading),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun PreviewLoadingNewsListScreen() {
    LoadingNewsListScreen()
}
