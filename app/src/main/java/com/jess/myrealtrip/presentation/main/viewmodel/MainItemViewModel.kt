package com.jess.myrealtrip.presentation.main.viewmodel

import androidx.databinding.ObservableField
import com.jess.myrealtrip.common.base.BaseItemViewModel
import com.jess.myrealtrip.common.extension.safeScope
import com.jess.myrealtrip.data.ChannelData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import timber.log.Timber

/**
 * Item ViewModel
 */
class MainItemViewModel(private val data: ChannelData.ItemData?) : BaseItemViewModel() {

    init {
        getElement()
    }

    val image = ObservableField<String>()
    val description = ObservableField<String>()

    private fun getDocument(): Document? {
        return data?.let {
            Timber.d(data.link)
            Jsoup.connect(data.link).get()
        } ?: run {
            null
        }
    }

    /**
     * 이미지, description 리턴
     */
    private fun getElement() {
        CoroutineScope(ioDispatchers).safeScope().launch {
            getDocument()?.let { doc ->
                val tags = doc.select("meta[property^=og:]")
//                var image: String? = null
//                var description: String? = null

                CoroutineScope(uiDispatchers).safeScope().launch {
                    tags.forEach { tag ->
                        when (tag.attr("property")) {
                            "og:image" -> {
                                image.set(tag.attr("content"))
                                Timber.d("image : ${image.get()}")
                            }

                            "og:description" -> {
                                description.set(tag.attr("content"))
                                Timber.d("description : ${description.get()}")
                            }
                        }
                    }
                }
            }
        }
    }
}