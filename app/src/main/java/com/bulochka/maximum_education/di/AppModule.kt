package com.bulochka.maximum_education.di

import com.bulochka.maximum_education.data.remote.NewsApiService
import com.bulochka.maximum_education.repository.Repository
import com.bulochka.maximum_education.ui.newsline.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(
        newsApiService: NewsApiService
    ) = Repository(newsApiService)

    @Provides
    fun provideNewsAdapter(): NewsAdapter = NewsAdapter()
}