package com.jess.myrealtrip.data

import java.io.Serializable

/**
 * @author jess
 * @since 2020.03.20
 */
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
                val pubDate: String,
                val source: SourceData
            ) {
                data class SourceData(
                    val content: String?,
                    val url: String?
                ) : Serializable
            }
        }
    }
}