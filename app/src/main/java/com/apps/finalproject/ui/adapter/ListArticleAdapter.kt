package com.apps.finalproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.databinding.ItemArticleBinding
import com.apps.finalproject.model.Article
import com.bumptech.glide.Glide

class ListArticleAdapter(private val listArticle: List<Article>) : RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemArticleBinding

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
                .circleCrop()
                .into(imPerawatanKulit)
            tvPenjelasanPerawatanKulit.text = article.title
            tvWaktuArtikel.text = article.date
        }
    }

    override fun getItemCount(): Int = listArticle.size
}