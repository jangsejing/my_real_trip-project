package com.jess.myrealtrip.repository

import com.jess.myrealtrip.data.RssData
import com.jess.myrealtrip.repository.service.GoogleService
import retrofit2.Response

/**
 * General Repository
 */
interface GoogleRepository {
    suspend fun getList(): Response<RssData>
}

class GoogleRepositoryImpl constructor(
    private val service: GoogleService
) : GoogleRepository {
    override suspend fun getList(): Response<RssData> = service.getList()
}
