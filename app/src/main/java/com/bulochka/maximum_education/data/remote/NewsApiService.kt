package com.bulochka.maximum_education.data.remote

import com.bulochka.maximum_education.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("latest-news?")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("page_number") pageNumber: String): NewsResponse
}