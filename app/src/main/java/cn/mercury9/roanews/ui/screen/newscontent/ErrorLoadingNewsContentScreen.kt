package cn.mercury9.roanews.ui.screen.newscontent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cn.mercury9.roanews.R
import cn.mercury9.roanews.data.NewsContentState
import cn.mercury9.roanews.data.model.NewsInfo

@Composable
fun ErrorLoadingNewsContentScreen(
    newsContentState: NewsContentState.ErrorLoadingNewsContent,
    target: NewsInfo,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(
                text = stringResource(id = R.string.fail_load_news_content).format(target.title),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .wrapContentSize()
            )
            Text(
                text = newsContentState.e.toString()
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
