package com.apps.finalproject.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.di.Injection
import com.apps.finalproject.remote.response.VariantsResponse
import com.apps.finalproject.ui.cart.CartViewModel
import com.apps.finalproject.ui.cart.GetCartViewModel
import com.apps.finalproject.ui.payment.PaymentViewModel
import com.apps.finalproject.ui.detail.DetailViewModel
import com.apps.finalproject.ui.favorite.FavoriteViewModel
import com.apps.finalproject.ui.home.HomeViewModel
import com.apps.finalproject.ui.login.LoginGoogleViewModel
import com.apps.finalproject.ui.login.LoginViewModel
import com.apps.finalproject.ui.order.OrdersViewModel
import com.apps.finalproject.ui.viewmodel.OngkirViewModel
import com.apps.finalproject.ui.viewmodel.ProfileviewModel
import com.apps.finalproject.ui.viewmodel.VariantViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(mainRepository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(mainRepository) as T
        } else  if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(mainRepository) as T
        } else  if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(mainRepository) as T
        } else  if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            return CartViewModel(mainRepository) as T
        } else  if (modelClass.isAssignableFrom(GetCartViewModel::class.java)){
            return GetCartViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(OngkirViewModel::class.java)){
            return OngkirViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(PaymentViewModel::class.java)){
            return PaymentViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(ProfileviewModel::class.java)){
            return ProfileviewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(OrdersViewModel::class.java)){
            return OrdersViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(VariantViewModel::class.java)){
            return VariantViewModel(mainRepository) as T
        }else  if (modelClass.isAssignableFrom(LoginGoogleViewModel::class.java)){
            return LoginGoogleViewModel(mainRepository) as T
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