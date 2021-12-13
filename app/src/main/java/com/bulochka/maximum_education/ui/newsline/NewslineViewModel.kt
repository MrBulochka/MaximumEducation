package com.bulochka.maximum_education.ui.newsline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulochka.maximum_education.data.model.News
import com.bulochka.maximum_education.repository.Repository
import com.bulochka.maximum_education.data.model.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewslineViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    private val _error = MutableLiveData<Exception?>()
    val error: LiveData<Exception?> get() = _error

    fun loadNewsWithException(pageNumber: String) {
        viewModelScope.launch {
            when (val result = repository.getNews(pageNumber)) {
                is ResultWrapper.Success -> {
                    _news.postValue(result.value.news)
                }
                is ResultWrapper.GenericError -> {
                    _error.postValue(result.error)
                }
            }
        }
    }
}