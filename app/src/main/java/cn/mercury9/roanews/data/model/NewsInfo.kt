package cn.mercury9.roanews.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsInfo(
    val title: String,
    val href: String
)
