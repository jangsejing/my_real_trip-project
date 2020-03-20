package com.jess.myrealtrip.data

import androidx.databinding.ObservableField
import com.jess.myrealtrip.data.RssResponseData.RssData.ChannelData.ItemData.SourceData
import java.io.Serializable

/**
 * @author jess
 * @since 2020.03.20
 */
class NewsData(
    val title: String? = null,
    val link: String? = null,
    val source: SourceData? = null,
    val description: ObservableField<String> = ObservableField(),
    val image: ObservableField<String> = ObservableField(),
    val tags: ObservableField<List<String>> = ObservableField()
) : Serializable
