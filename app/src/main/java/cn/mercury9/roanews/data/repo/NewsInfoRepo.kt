package cn.mercury9.roanews.data.repo

import android.util.Log
import cn.mercury9.roanews.data.model.NewsInfo
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

interface NewsInfoRepo {
    suspend fun getNewsList(): List<NewsInfo>
}

class NetworkNewsInfoRepo(
    private val client: HttpClient,
    private val url: String = "https://roa.ruogustudio.net/res/data/news_list.json"
): NewsInfoRepo {
    override suspend fun getNewsList(): List<NewsInfo> {
        Log.i("load_data NetworkNewsInfoRepo", "Loading News List from $url")
        val response = client.get(url)
        if (response.status.value != 200) {
            Log.e("load_data NetworkNewsInfoRepo", "Loading News List Failed: Response code ${response.status.value}")
            throw Error("Loading News List Failed: Response code ${response.status.value}")
        }
        val responseBody = response.bodyAsText()
        Log.i("load_data NetworkNewsInfoRepo", "Successful load News List from $url")
        return Json.decodeFromString<List<NewsInfo>>(responseBody)
    }
}
