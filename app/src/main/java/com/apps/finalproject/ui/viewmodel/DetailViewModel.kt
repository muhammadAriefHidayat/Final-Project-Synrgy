package com.apps.finalproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.Review
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: MainRepository): ViewModel(){
    private var _review = MutableLiveData<List<Review>>()
    val review get() = _review

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getReview(userId: String) = viewModelScope.launch {
        repository.getReview(userId).collect{
            _review.value = it
        }
    }
}