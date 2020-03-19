package com.jess.myrealtrip.data

class RssData(
    val status: String?,
    val feed: FeedData?,
    val items: List<ItemData>?
) {

    data class FeedData(
        val url: String?,
        val title: String?,
        val link: String?,
        val description: String?
    )

    data class ItemData(
        val title: String?,
        val pubDate: String?,
        val link: String?,
        val author: String?,
        val thumbnail: String?,
        val description: String?,
        val content: String?
    )
}
