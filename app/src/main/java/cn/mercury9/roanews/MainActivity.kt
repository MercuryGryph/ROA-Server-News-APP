package cn.mercury9.roanews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cn.mercury9.roanews.ui.RoaNewsApp
import cn.mercury9.roanews.ui.theme.RoaNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoaNewsTheme {
                RoaNewsApp()
            }
        }
    }
}
