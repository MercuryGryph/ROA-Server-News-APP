package cn.mercury9.roanews.data.repo

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

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
            Log.e("load_data NetworkNewsContentRepo", "Loading News Failed: Response code ${response.status.value}")
            throw Error("Loading News Failed: Response code ${response.status.value}; ${response.bodyAsText()}")
        }
        return response.bodyAsText()
    }
}
