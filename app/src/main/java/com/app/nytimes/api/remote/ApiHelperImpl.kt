package com.app.nytimes.api.remote

import com.app.nytimes.data.FeedResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getMostPopularFeed(
        period: Int, apiKey: String
    ): Response<FeedResponse> =
        apiService.getMostPopularFeed(period, apiKey)
}