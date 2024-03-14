package cn.mercury9.roanews.data.repo

import cn.mercury9.roanews.data.model.NewsInfo
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

interface NewsContentRepo {
    suspend fun getNewsContent(target: String): String
}

class NetworkNewsContentRepo(
    private val client: HttpClient,
    private val url: String = "https://roa.ruogustudio.net/docs/static/"
): NewsContentRepo {
    override suspend fun getNewsContent(target: String): String {
        val response = client.get("$url$target.md")
        if (response.status.value != 200) {
            throw Error()
        }
        return response.bodyAsText()
    }
}
