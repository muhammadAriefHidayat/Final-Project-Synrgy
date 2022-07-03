package com.apps.finalproject.ui.adapter

import Variant
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemListProdukTrendingBinding
import com.apps.finalproject.model.Trending
import com.apps.finalproject.ui.adapter.ListTrendingAdapter.*
import com.bumptech.glide.Glide
import toListVariant

class ListTrendingAdapter(private val listTrending: List<Trending>) : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListProdukTrendingBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListProdukTrendingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productTrending = listTrending[position]
        with(binding){
            Glide.with(binding.root.context)
                .load(productTrending.images[0])
                .centerCrop()
                .placeholder(R.drawable.article_herbal)
                .into(imageView2)
            textView2.text = productTrending.brand.name
            tvNameProduk.text = productTrending.variant.toListVariant()[0].name
            textView3.text = productTrending.variant.toListVariant()[0].price.toString()
            ratingBar2.rating = productTrending.average.toFloat()
        }
    }

    override fun getItemCount(): Int = listTrending.size
}