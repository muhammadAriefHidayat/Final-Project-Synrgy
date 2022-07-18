package com.apps.finalproject.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityCartBinding
import com.apps.finalproject.remote.response.CartItems
import com.apps.finalproject.remote.response.CartOverview
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.checkoutshiping.CheckoutActivity
import com.apps.finalproject.ui.detail.DetailViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CartActivity : AppCompatActivity() {
    lateinit var binding : ActivityCartBinding
    private val adapterrv = GroupAdapter<GroupieViewHolder>()

    private val cartViewmodel: GetCartViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cartViewmodel.GetCart()
        cartViewmodel.getOverview().observe(this){
            setData(it)
        }
        cartViewmodel.getCartItems().observe(this){
            setCartItems(it)
        }
        binding.btnCheckout.setOnClickListener{
            startActivity(Intent(this,CheckoutActivity::class.java))
        }
    }

    private fun setCartItems(it: List<CartItems>?) {
        if(it != null){
            it.forEach { cartItems ->
                adapterrv.add(CartBrandAdapter(cartItems,this))
            }
            binding.rvCart.apply {
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                adapter = adapterrv
            }
        }else{
            Log.d("sdf","err")
        }
    }

    private fun setData(it: CartOverview?) {
        if (it != null){
            binding.tvSubtotal.text = it.total.toString()
        }
    }
}