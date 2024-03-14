package cn.mercury9.roanews.data

import cn.mercury9.roanews.data.model.NewsInfo
import cn.mercury9.roanews.data.repo.NetworkNewsContentRepo
import cn.mercury9.roanews.data.repo.NetworkNewsInfoRepo
import cn.mercury9.roanews.data.repo.NewsContentRepo
import cn.mercury9.roanews.data.repo.NewsInfoRepo
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

interface AppContainer {
    var newsInfoList: List<NewsInfo>
    var newsContentMap: MutableMap<String, String>

    val newsInfoRepo: NewsInfoRepo
    val newsContentRepo: NewsContentRepo
}

class DefaultAppContainer: AppContainer {
    override var newsInfoList: List<NewsInfo> = listOf()
    override var newsContentMap: MutableMap<String, String> = mutableMapOf()

    private val client = HttpClient(OkHttp)

    override val newsInfoRepo: NewsInfoRepo by lazy {
        NetworkNewsInfoRepo(client)
    }

    override val newsContentRepo: NewsContentRepo by lazy {
        NetworkNewsContentRepo(client)
    }
}
