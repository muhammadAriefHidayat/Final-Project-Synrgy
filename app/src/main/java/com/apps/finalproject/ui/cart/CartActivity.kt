package com.apps.finalproject.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.databinding.ActivityCartBinding
import com.apps.finalproject.remote.response.CartItems
import com.apps.finalproject.remote.response.CartOverview
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.checkoutshiping.CheckoutActivity
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
            if (it?.isNotEmpty() == true){
                setCartItems(it)
            }else{
                binding.imgCartKosong.visibility = View.VISIBLE
                binding.tvCartkosong.visibility = View.VISIBLE
                binding.rvCart.visibility = View.GONE
            }
        }
        binding.btnCheckout.setOnClickListener{
            startActivity(Intent(this,CheckoutActivity::class.java))
        }
    }

    private fun setData(it: CartOverview?) {
        if ((it?.total != 0 ) and (it?.total!== null)){
            binding.apply {
                tvCartkosong.visibility = View.GONE
                imgCartKosong.visibility = View.GONE
                tvSubtotal.text = it?.total.toString()
            }
        }else{
            binding.apply {
                imgCartKosong.visibility = View.VISIBLE
                tvCartkosong.visibility = View.VISIBLE
            }
        }
        binding.progress.visibility = View.GONE
    }

    private fun setCartItems(it: List<CartItems>?) {
        if(it?.isNotEmpty() == true){
            it.forEach { cartItems ->
                adapterrv.add(CartBrandAdapter(cartItems,this))
            }
            binding.rvCart.apply {
                visibility = View.VISIBLE
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                adapter = adapterrv
            }
            binding.imgCartKosong.visibility = View.GONE
        }else{
            binding.imgCartKosong.visibility = View.VISIBLE
            binding.tvCartkosong.visibility = View.VISIBLE
            binding.rvCart.visibility = View.GONE
        }
    }
}