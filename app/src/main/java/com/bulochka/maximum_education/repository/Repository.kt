package com.bulochka.maximum_education.repository

import com.bulochka.maximum_education.BuildConfig
import com.bulochka.maximum_education.data.remote.CustomNetworkCall
import com.bulochka.maximum_education.data.remote.NewsApiService
import com.bulochka.maximum_education.data.model.NewsResponse
import com.bulochka.maximum_education.data.model.ResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val newsApiService: NewsApiService
): CustomNetworkCall() {

    suspend fun getNews(pageNumber: String
    ): ResultWrapper<NewsResponse> {
        return safeApiCall {
            newsApiService.getNews(
                BuildConfig.API_KEY,
                pageNumber)
        }
    }
}