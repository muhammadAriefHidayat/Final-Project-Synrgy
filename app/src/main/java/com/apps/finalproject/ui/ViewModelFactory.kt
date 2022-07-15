package com.apps.finalproject.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.di.Injection
import com.apps.finalproject.ui.cart.CartViewModel
import com.apps.finalproject.ui.cart.GetCartViewModel
import com.apps.finalproject.ui.detail.DetailViewModel
import com.apps.finalproject.ui.home.HomeViewModel
import com.apps.finalproject.ui.login.LoginViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(mainRepository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            return CartViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(GetCartViewModel::class.java)){
            return GetCartViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also {
                instance = it
            }
    }
}