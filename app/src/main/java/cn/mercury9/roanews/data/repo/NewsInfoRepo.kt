package cn.mercury9.roanews.data.repo

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
        val response = client.get(url)
        if (response.status.value != 200) {
            throw Error()
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString<List<NewsInfo>>(responseBody)
    }
}
