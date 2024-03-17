package cn.mercury9.roanews.ui.screen.newscontent

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cn.mercury9.roanews.R
import cn.mercury9.roanews.data.model.NewsInfo

@Composable
fun LoadingNewsContentScreen(
    target: NewsInfo,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.loading_news_content)
                .format(target),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
        )
    }
}
