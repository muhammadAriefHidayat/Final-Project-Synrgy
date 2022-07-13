package com.apps.finalproject.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.Brand
import com.apps.finalproject.remote.model.Review
import com.apps.finalproject.remote.model.Trending
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: MainRepository): ViewModel(){
    private var _review = MutableLiveData<List<Review>>()
    val review : LiveData<List<Review>> = _review

    fun getReview() = viewModelScope.launch {
        repository.getReview().collect{
             _review.value = it
        }
    }
}