package com.apps.finalproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.RegisterBody
import com.apps.finalproject.model.Review
import kotlinx.coroutines.launch

class RegisterViewModel (private val repository: MainRepository): ViewModel() {
    private var mRegister = MutableLiveData<String>()
    val register get() = mRegister

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun postRegister(registerBody: RegisterBody) = viewModelScope.launch {
        repository.register(registerBody).collect{
            mRegister.value = it.token
        }
    }
}
