package cn.mercury9.roanews.ui.screen.newscontent

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import cn.mercury9.roanews.R
import cn.mercury9.roanews.data.NewsContentState
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

@Composable
fun NewsContentScreen(
    newsContentState: NewsContentState.SuccessLoadNewsContent,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
    ) {
        val flavour = CommonMarkFlavourDescriptor()
        val parsedTree = MarkdownParser(flavour)
            .buildMarkdownTreeFromString(newsContentState.newsContent)
        val html = HtmlGenerator(
            newsContentState.newsContent,
            parsedTree,
            flavour
        ).generateHtml() +
        stringResource(id = R.string.html_style_markdown)
            .replace("\${bg_color}", MaterialTheme.colorScheme.background.toCssRgbaString())
            .replace("\${color_p}", MaterialTheme.colorScheme.primary.toCssRgbaString())
            .replace("\${font_color_1}", MaterialTheme.colorScheme.onBackground.toCssRgbaString())
            .replace("\${font_color_time}", "#888")
            .replace("\${font_color_code}", "#888")
            .replace("\${bg_color_code}", "#333")

        Column {
            WebViewCompose(data = html)

//            Text(text = html)
        }
    }
}

fun Color.toCssRgbaString(): String {
    return "rgb(" +
            "${(this.red * 255).toInt()}, " +
            "${(this.green * 255).toInt()}, " +
            "${(this.blue * 255).toInt()}, " +
            "${(this.alpha)}" +
            ")"
}

@Composable
fun WebViewCompose(
    data: String,
    modifier: Modifier = Modifier
) {
    val webViewClient = object: WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return true
        }
    }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                this.webViewClient = webViewClient

                loadDataWithBaseURL(
                    null,
                    data,
                    "text/html",
                    "utf-8",
                    null
                )

            }
        }
    )
}
