package cn.mercury9.roanews

import android.app.Application
import cn.mercury9.roanews.data.AppContainer
import cn.mercury9.roanews.data.DefaultAppContainer
import cn.mercury9.roanews.data.model.NewsInfo

class App: Application() {
    lateinit var container: AppContainer

    lateinit var curlNewsContent: NewsInfo

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
