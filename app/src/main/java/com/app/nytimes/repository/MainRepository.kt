package com.app.nytimes.repository

import com.app.nytimes.api.remote.ApiService
import com.app.nytimes.data.FeedResponse
import retrofit2.Response
import javax.inject.Inject
import com.app.nytimes.BuildConfig

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMostPopularFeeds(period: Int): Response<FeedResponse> {
        return apiService.getMostPopularFeed(period, apiKey = BuildConfig.API_KEY)
    }
}