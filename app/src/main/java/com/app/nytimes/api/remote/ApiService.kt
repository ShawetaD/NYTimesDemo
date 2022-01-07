package com.app.nytimes.api.remote

import com.app.nytimes.api.constants.Params
import com.app.nytimes.data.FeedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/svc/mostpopular/v2/viewed/{period}.json?")
    suspend fun getMostPopularFeed(
        @Path(Params.PERIOD) period: Int,
        @Query(Params.API_KEY) apiKey: String
    ): Response<FeedResponse>

}