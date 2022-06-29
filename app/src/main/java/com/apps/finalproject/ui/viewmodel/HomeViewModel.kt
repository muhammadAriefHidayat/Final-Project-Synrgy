package com.apps.finalproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.Article
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository): ViewModel() {
    private var _article = MutableLiveData<List<Article>>()
    val article : LiveData<List<Article>> = _article

    fun getArticle() = viewModelScope.launch {
        repository.getArticle().collect{
            _article.value = it
        }
    }
}