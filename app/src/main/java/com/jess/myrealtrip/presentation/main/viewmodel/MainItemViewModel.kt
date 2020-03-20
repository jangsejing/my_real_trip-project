package com.jess.myrealtrip.presentation.main.viewmodel

import com.jess.myrealtrip.common.base.BaseItemViewModel
import com.jess.myrealtrip.common.extension.safeScope
import com.jess.myrealtrip.common.util.StringUtils
import com.jess.myrealtrip.common.util.tryCatch
import com.jess.myrealtrip.data.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import timber.log.Timber
import java.util.*
import kotlin.collections.HashMap


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
        data?.let {
            CoroutineScope(ioDispatchers).safeScope().launch {
                getDocument()?.let { doc ->
                    CoroutineScope(uiDispatchers).safeScope().launch {
                        setImage(doc)
                        setDescription(doc)
                    }
                    createTags(doc)
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
                val result = if (ogImage.isNullOrEmpty()) {
                    doc.select("meta[name=image]")?.first()?.attr("content")
                } else {
                    ogImage
                }
                data?.image?.set(result)
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
                    doc.select("meta[name=description]")?.first()?.attr("content")
                } else {
                    ogDesc
                }
                data?.description?.set(result)
            }
        }
    }

    /**
     * Description을 분석하여 태그 생성
     * - 2글자 이상, 띄어쓰기만 고려
     * - 빈도수가 높은 3건순으로 정렬 (빈도수가 같을 경우 오름차순 적용)
     */
    private fun createTags(doc: Document) {

        val tagList = data?.tags?.get()
        if (!tagList.isNullOrEmpty()) {
            return
        }

        val body = doc.select("body").text()
        body?.let {
            val st = StringTokenizer(body, " ")
            val map = HashMap<String, Int>()
            while (st.hasMoreTokens()) {
                val token = st.nextToken()
                if (token.length < 2 || StringUtils.hasSpecialText(token)) {
                    // 두글자 미만, 특수문자일 경우 제외
                    continue
                }
                val isExistKey = map.containsKey(token)
                if (isExistKey) {
                    map[token] = map[token]?.plus(1) ?: 1
                } else {
                    map[token] = 1
                }
            }
//            Timber.d(map.toString())

            // 정렬
            val list = LinkedList(map.entries)
            list.sortWith(Comparator { o1, o2 ->
                val comparision = (o1.value - o2.value) * -1
                if (comparision == 0) o1.key.compareTo(o2.key) else comparision
            })

            // 정렬된 리스트 Add
            val tags = mutableListOf<String>()
            val iterator = list.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                if (tags.size < 3) {
                    tags.add(entry.key)
                }
            }
            data?.tags?.set(tags)
        }
    }
}