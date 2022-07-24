package com.apps.finalproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemListBeautyFeedBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.ui.article.ListArticleAdapter
import com.bumptech.glide.Glide

class ListBeautyFeedAdapter (private val listFeed: List<Article>) : RecyclerView.Adapter<ListBeautyFeedAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListBeautyFeedBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = ItemListBeautyFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feed = listFeed[position]
        with(binding){
            Glide.with(binding.root.context)
                .load(feed.photo)
                .centerCrop()
                .placeholder(R.drawable.article_herbal)
                .into(ivBanner)
            tvTitle.text = feed.title
            tvDate.text = feed.date
        }
    }

    override fun getItemCount(): Int = listFeed.size
}