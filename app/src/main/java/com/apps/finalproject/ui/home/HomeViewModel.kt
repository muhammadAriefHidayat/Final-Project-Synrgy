package com.apps.finalproject.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.*
import com.apps.finalproject.remote.response.ProductsItemResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository): ViewModel() {
    private var _article = MutableLiveData<List<Article>>()
    val article : LiveData<List<Article>> = _article

    private var _productTrending = MutableLiveData<List<Trending>>()
    val trending : LiveData<List<Trending>> = _productTrending

    private var _productItem = MutableLiveData<List<ProductsItem>>()
    val productsItem : LiveData<List<ProductsItem>> = _productItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getArticle() = viewModelScope.launch {
        _isLoading.value = true
        repository.getArticle().collect{
            Log.d("articel", it.toString())
            _article.value = it
            _isLoading.value = false
        }
    }

    fun getProductTrending() = viewModelScope.launch {
        repository.getProductTrending().collect{
            Log.d("articel", it.toString())
            _isLoading.value = true
            _productTrending.value = it
            _isLoading.value = false
        }
    }

    fun searchProductByName(name: String) = viewModelScope.launch {
        repository.searchProductByName(name).collect{
            Log.d(TAG, "searchProductByName: ${it.toListProductItem()}")
            _productItem.value = it.toListProductItem()
        }
    }
}