package com.apps.finalproject.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.local.entity.Favorite
import com.apps.finalproject.remote.model.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: MainRepository): ViewModel(){
    private var _review = MutableLiveData<List<Review>>()
    val review : LiveData<List<Review>> = _review

    fun getReview() = viewModelScope.launch {
        repository.getReview().collect{
             _review.value = it
        }
    }

    private val _myFavorite = MutableLiveData<Boolean>()
    val myFavorite get() = _myFavorite

    fun getMyFavorite(productName: String) = viewModelScope.launch {
        repository.getMyFavorite(productName).collect{
            _myFavorite.value = it != null
        }
    }

    fun addFavorite(favoriteProduct: Trending) = viewModelScope.launch {
        favoriteProduct.toFavorite()?.let { repository.addFavorite(it) }
    }

    fun deleteFavorite(favoriteProduct: Trending) = viewModelScope.launch {
        favoriteProduct.toFavorite()?.let { repository.deleteFavorite(it) }
    }
}