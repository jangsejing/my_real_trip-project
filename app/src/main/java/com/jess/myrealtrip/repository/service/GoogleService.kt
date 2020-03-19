package com.jess.myrealtrip.repository.service

import com.jess.myrealtrip.data.RssData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleService {

    /**
     * News RSS 조회
     */
    @GET("/v1/api.json")
    suspend fun getList(
        @Query("rss_url") url: String = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    ): Response<RssData>
}