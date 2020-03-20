package com.jess.myrealtrip.data

data class ChannelData(
    val item: List<ItemData>?,
    val lastBuildDate: String?,
    val link: String?,
    val description: String?,
    val language: String?,
    val title: String?
) {

    data class ItemData(
        val link: String?,
        val description: String,
        val title: String,
        val pubDate: String,
        val source: SourceData?
    ) {

        data class SourceData(
            val content: String?,
            val url: String
        )
    }
}
