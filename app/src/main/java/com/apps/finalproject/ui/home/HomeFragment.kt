package com.apps.finalproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.databinding.FragmentHomeBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListTrendingAdapter
import com.apps.finalproject.ui.article.ListArticleAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getArticle()
        homeViewModel.article.observe(viewLifecycleOwner){
            showDataArticle(it)
        }

        homeViewModel.getProductTrending()
        homeViewModel.trending.observe(viewLifecycleOwner){
            showProductTrending(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showDataArticle(listArticle: List<Article>) {
        val listArticleAdapter = ListArticleAdapter(listArticle)
        binding.itemListArticle.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listArticleAdapter
        }
    }

    private fun showProductTrending(listProductTrending: List<Trending>){
        val listTrendingAdapter = ListTrendingAdapter(listProductTrending)
        binding.listProdukTrending.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listTrendingAdapter
        }
    }
}