package cn.mercury9.roanews

import android.app.Application
import cn.mercury9.roanews.data.AppContainer
import cn.mercury9.roanews.data.DefaultAppContainer

class App: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
