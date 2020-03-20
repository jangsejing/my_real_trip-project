package com.jess.myrealtrip.repository.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author jess
 * @since 2020.03.20
 */
interface GoogleService {

    /**
     * News RSS 조회
     * https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko
     */
    @GET("/rss")
    suspend fun getList(
        @Query("hl") hl : String = "ko",
        @Query("ceid") ceid : String = "KR:ko"
    ): Response<String>

}