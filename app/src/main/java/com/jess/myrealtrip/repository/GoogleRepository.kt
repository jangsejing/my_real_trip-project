package com.jess.myrealtrip.repository

import com.google.gson.Gson
import com.jess.myrealtrip.common.util.tryCatch
import com.jess.myrealtrip.data.RssResponseData
import com.jess.myrealtrip.repository.service.GoogleService
import fr.arnaudguyon.xmltojsonlib.XmlToJson

/**
 * @author jess
 * @since 2020.03.20
 */
interface GoogleRepository {
    suspend fun getList(): RssResponseData.RssData.ChannelData?
}

class GoogleRepositoryImpl constructor(
    private val service: GoogleService
) : GoogleRepository {

    /**
     * XML을 Data Class로 변환
     * com.github.smart-fun:XmlToJson 사용
     * @return
     */
    override suspend fun getList(): RssResponseData.RssData.ChannelData? {
        tryCatch {
            val response = service.getList().body()
            response?.let {
                val json = XmlToJson.Builder(response).build().toJson().toString()
                val data = Gson().fromJson(json, RssResponseData::class.java)
                return data.rss?.channel
            }
        }
        return null
    }
}
