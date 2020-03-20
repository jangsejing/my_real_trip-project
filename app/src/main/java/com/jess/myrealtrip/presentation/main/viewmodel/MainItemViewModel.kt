package com.jess.myrealtrip.presentation.main.viewmodel

import com.jess.myrealtrip.common.base.BaseItemViewModel
import com.jess.myrealtrip.common.extension.safeScope
import com.jess.myrealtrip.common.util.tryCatch
import com.jess.myrealtrip.data.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import timber.log.Timber

/**
 * Item ViewModel
 */
class MainItemViewModel(private val data: NewsData?) : BaseItemViewModel() {

    init {
        getElement()
    }

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
//        val meta = doc.select("meta[name=description]").first().select("content")
        data?.let {
            CoroutineScope(ioDispatchers).safeScope().launch {
                getDocument()?.let { doc ->
                    CoroutineScope(uiDispatchers).safeScope().launch {
                        setImage(doc)
                        setDescription(doc)
                    }
                }
            }
        }
    }

    /**
     * 이미지
     *
     * @param doc
     */
    private fun setImage(doc: Document) {
        tryCatch {
            val image = data?.image?.get()
            if (image.isNullOrEmpty()) {
                val ogImage = doc.select("meta[property=og:image]")?.first()?.attr("content")
                data?.image?.set(ogImage)
            }
        }
    }

    /**
     * 이미지
     * og:description가 없는 케이스는 name=description로 대체
     *
     * @param doc
     */
    private fun setDescription(doc: Document) {
        tryCatch {
            val description = data?.description?.get()
            if (description.isNullOrEmpty()) {
                val ogDesc = doc.select("meta[property=og:description]")?.first()?.attr("content")
                val result = if (ogDesc.isNullOrEmpty()) {
                    doc.select("meta[name=description]").first().attr("content")
                } else {
                    ogDesc
                }
                data?.description?.set(result)
            }
        }
    }
}