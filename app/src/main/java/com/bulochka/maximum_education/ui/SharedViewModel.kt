package com.bulochka.maximum_education.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulochka.maximum_education.data.model.News

//Для передачи данных между фрагментами
class SharedViewModel: ViewModel() {

    private val _news = MutableLiveData<News>()
    val news: LiveData<News> get() = _news

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    fun selectNews(news: News) {
        _news.value = news
    }

    fun selectImageUrl(imageUrl: String) {
        _imageUrl.value = imageUrl
    }
}