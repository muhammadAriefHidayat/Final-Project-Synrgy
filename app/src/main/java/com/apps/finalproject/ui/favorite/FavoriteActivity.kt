package com.apps.finalproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.databinding.ActivityFavoriteBinding
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.remote.model.toListProduct
import com.apps.finalproject.ui.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getFavorite()

        viewModel.favorite.observe(this) {
            showRecylerList(it.toListProduct())
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showRecylerList(listProduct: List<Trending>){
        val listFavoriteAdapter = ListFavoriteAdapter(listProduct)
        binding.tvFavorites.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listFavoriteAdapter
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        binding.progressBar.visibility = if (isLoading == true) View.VISIBLE else View.GONE
    }

}