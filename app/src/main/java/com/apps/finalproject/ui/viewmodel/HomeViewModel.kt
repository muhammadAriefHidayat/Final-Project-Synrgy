package com.apps.finalproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.Article
import com.apps.finalproject.model.Trending
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository): ViewModel() {
    private var _article = MutableLiveData<List<Article>>()
    val article : LiveData<List<Article>> = _article

    private var _productTrending = MutableLiveData<List<Trending>>()
    val trending : LiveData<List<Trending>> = _productTrending

        private val _isLoading = MutableLiveData<Boolean>()
        val isLoading: LiveData<Boolean> = _isLoading

    fun getArticle() = viewModelScope.launch {
        _isLoading.value = true
        repository.getArticle().collect{
            _article.value = it
            _isLoading.value = false
        }
    }

    fun getProductTrending() = viewModelScope.launch {
        repository.getProductTrending().collect{
            _isLoading.value = true
            _productTrending.value = it
            Log.d("getTrending", trending.value.toString())
            _isLoading.value = false
        }
    }
}