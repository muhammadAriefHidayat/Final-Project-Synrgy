package com.apps.finalproject.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.FavoriteProduct
import kotlinx.coroutines.launch

class FavoriteViewModel (
    private val mainRepository: MainRepository
): ViewModel(){

    private val _favorite = MutableLiveData<List<FavoriteProduct>>()
    val favorite: LiveData<List<FavoriteProduct>> = _favorite

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFavorite() = viewModelScope.launch {
        _isLoading.value = true
        mainRepository.getAllFavorite().collect{
            _favorite.value = it
            _isLoading.value = false
        }
    }
}