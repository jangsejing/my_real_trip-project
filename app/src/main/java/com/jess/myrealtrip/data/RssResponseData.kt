package com.jess.myrealtrip.data

class RssResponseData(
    val rss: RssData?
) {
    data class RssData(
        val channel: ChannelData?
    ) {
        data class ChannelData(
            val item: List<ItemData>?
        ) {
            data class ItemData(
                val link: String?,
                val description: String,
                val title: String,
                val pubDate: String
            )
        }
    }
}