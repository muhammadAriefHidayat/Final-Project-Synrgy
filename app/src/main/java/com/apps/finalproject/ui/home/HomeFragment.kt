package com.apps.finalproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentHomeBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.remote.model.ProductsItem
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListTrendingAdapter
import com.apps.finalproject.ui.article.DetailArticleFragment.Companion.EXTRA_ARTICLE
import com.apps.finalproject.ui.article.ListArticleAdapter
import com.apps.finalproject.ui.cart.CartActivity
import com.apps.finalproject.ui.detail.DetailFragment.Companion.EXTRA_PRODUCT
import com.apps.finalproject.ui.view.AuthActivity
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils
import com.apps.finalproject.utils.objectToString
import com.apps.finalproject.utils.stringToObject

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

        binding.itemHeader.username.text = AppPref.username
        homeViewModel.getArticle()
        homeViewModel.article.observe(viewLifecycleOwner){
            showDataArticle(it)
        }

        val dataProduct = ProductsItem::class.java
        dataProduct.name.let {
            homeViewModel.searchProductByName(it)
        }
        homeViewModel.productsItem.observe(viewLifecycleOwner){

        }

        homeViewModel.getProductTrending()
        homeViewModel.trending.observe(viewLifecycleOwner){
            showProductTrending(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        binding.itemHeader.itemSearch.ivSearch.setOnClickListener{
            searchProduct(homeViewModel)
        }

        binding.itemHeader.itemSearch.search.setOnEditorActionListener{ _, productId, _ ->
            if (productId == EditorInfo.IME_ACTION_NEXT) {
                searchProduct(homeViewModel)
            }
            true
        }
        binding.itemHeader.itemSearch.menuKeranjang.setOnClickListener {
            if (AppPref.token == ""){
                Utils.peringatan(requireContext(),"harap login terlebih dahulu")
                startActivity(Intent(requireContext(), AuthActivity::class.java))
            }else{
                startActivity(Intent(requireContext(),CartActivity::class.java))
            }
        }
        binding.tvLihatProdukTrending.setOnClickListener{
            view.findNavController().navigate(R.id.action_HomeFragment_to_productFragment)
        }
        binding.tvLihatSemuaArtikel.setOnClickListener{
            view.findNavController().navigate(R.id.action_HomeFragment_to_articleListFragment)
        }
    }

    private fun searchProduct(mainViewModel: HomeViewModel){
        mainViewModel.searchProductByName(binding.itemHeader.itemSearch.search.text.toString())
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showDataArticle(listArticle: List<Article>) {
        val listArticleAdapter = ListArticleAdapter(listArticle)
        listArticleAdapter.setOnItemClickListener(onItemClick)
        binding.itemListArticle.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listArticleAdapter
        }
    }

    private val onItemClick = object : ListArticleAdapter.OnItemClickListener{
        override fun onItemClick(article: Article) {
            detailArticle(article)
        }
    }

    private fun detailArticle(article: Article) {
        val bundle = bundleOf(EXTRA_ARTICLE to objectToString(article))
        Navigation.findNavController(requireView()).navigate(R.id.action_HomeFragment_to_detailArticleFragment, bundle)
    }

    private fun showProductTrending(listProductTrending: List<Trending>){
        val listTrendingAdapter = ListTrendingAdapter(listProductTrending)
        listTrendingAdapter.setOnItemClickListener(onItemClicked)
        binding.listProdukTrending.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = listTrendingAdapter
        }
    }

    private val onItemClicked = object : ListTrendingAdapter.OnItemClickListener{
        override fun onItemClicked(data: Trending) {
            detailTrending(data)
        }
    }

    private fun detailTrending(data: Trending){
        val mBundle = bundleOf(EXTRA_PRODUCT to objectToString(data))

        Navigation.findNavController(requireView()).navigate(R.id.action_HomeFragment_to_DetailFragment, mBundle)

    }
}