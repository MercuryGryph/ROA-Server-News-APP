package cn.mercury9.roanews.ui.screen.newscontent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.mercury9.roanews.data.NewsContentState

@Composable
fun NewsContentScreen(
    newsContentState: NewsContentState.SuccessLoadNewsContent,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
    ) {
        Text(
            text = newsContentState.newsContent,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
