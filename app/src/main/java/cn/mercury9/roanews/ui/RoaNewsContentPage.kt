package cn.mercury9.roanews.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import cn.mercury9.roanews.R
import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.ui.screen.NewsViewModel
import cn.mercury9.roanews.ui.theme.RoaNewsTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RoaNewsContentPage(
    navController: NavController,
    newsViewModel: NewsViewModel
) {
    val newsInfo: NewsInfo = newsViewModel.getNewsContentTarget()
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
        Surface {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoaNewsContentTopBar(
    title: String,
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
//                    tint = MaterialTheme.colorScheme.primary
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
        }
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
