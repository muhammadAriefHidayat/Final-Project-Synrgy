package com.apps.finalproject.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.apps.finalproject.databinding.ActivityOrderBinding
import com.apps.finalproject.remote.response.DataOrders
import com.apps.finalproject.ui.ViewModelFactory

class OrderActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityOrderBinding
    private val ordersViewModel:OrdersViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ordersViewModel.getOrders()
        ordersViewModel.getResponse().observe(this){
            if (it?.isNotEmpty() == true){
                setData(it)
            }
        }
    }

    fun setData(list: List<DataOrders>) {
        
    }
}