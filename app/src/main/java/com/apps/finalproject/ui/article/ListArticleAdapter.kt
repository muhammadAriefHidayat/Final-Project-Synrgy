package com.apps.finalproject.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemArticleBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.ui.adapter.ListTrendingAdapter
import com.bumptech.glide.Glide

class ListArticleAdapter(private val listArticle: List<Article>) : RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemArticleBinding

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = listArticle[position]
        with(binding){
            Glide.with(binding.root.context)
                .load(article.photo)
                .centerCrop()
                .placeholder(R.drawable.article_herbal)
                .into(imPerawatanKulit)
            tvPenjelasanPerawatanKulit.text = article.title
            tvWaktuArtikel.text = article.date
            root.setOnClickListener{
                onItemClickListener.onItemClick(article)
            }
        }
    }

    override fun getItemCount(): Int = listArticle.size

    interface OnItemClickListener{
        fun onItemClick(data: Article)
    }
}