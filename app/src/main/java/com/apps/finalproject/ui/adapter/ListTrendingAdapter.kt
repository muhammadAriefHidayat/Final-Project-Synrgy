package com.apps.finalproject.ui.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemListProdukTrendingBinding
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.ui.adapter.ListTrendingAdapter.*
import com.apps.finalproject.utils.Utils
import com.bumptech.glide.Glide

class ListTrendingAdapter(private val listTrending: List<Trending>) : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListProdukTrendingBinding
    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

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
                .load(productTrending.images?.get(0))
                .centerCrop()
                .placeholder(R.drawable.article_herbal)
                .into(imageView2)
            textView2.text = productTrending.brand?.name ?: ""
            tvNameProduk.text = productTrending.variant?.get(0)?.name ?: ""
            textView3.text = Utils.rupiah(productTrending.variant?.get(0)?.price!!.toDouble())
            ratingBar2.rating = productTrending.average?.toFloat()!!
            root.setOnClickListener{
                Log.d(TAG, "onBindViewHolder: ${productTrending.variant?.get(0)?.name}")
                onItemClickListener.onItemClicked(productTrending)
            }
        }
    }

    override fun getItemCount(): Int = listTrending.size

    interface OnItemClickListener{
        fun onItemClicked(data: Trending)
    }
}