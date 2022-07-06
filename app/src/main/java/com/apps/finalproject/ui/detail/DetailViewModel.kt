package com.apps.finalproject.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.Review
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: MainRepository): ViewModel(){
    private var _review = MutableLiveData<List<Review>>()
    val review : LiveData<List<Review>> = _review

//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading

    fun getReview() = viewModelScope.launch {
        repository.getReview().collect{

//            val mReview = it.data.forEach { review ->
                _review.value = it
                Log.d("getReview", review.value.toString())
//            }
        }
    }
}