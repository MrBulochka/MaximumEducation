package com.bulochka.maximum_education.di

import com.bulochka.maximum_education.ui.newsline.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNewsAdapter(): NewsAdapter = NewsAdapter()
}