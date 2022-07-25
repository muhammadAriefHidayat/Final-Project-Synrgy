package com.apps.finalproject.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentDetailArticleBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListTrendingAdapter
import com.apps.finalproject.ui.home.HomeViewModel
import com.apps.finalproject.utils.Utils
import com.apps.finalproject.utils.stringToObject
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class DetailArticleFragment : Fragment() {
    private lateinit var binding: FragmentDetailArticleBinding

    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataArticle = arguments?.getString(EXTRA_ARTICLE)
        val article = stringToObject(dataArticle, Article::class.java)
        populateDataAtricle(article)

        homeViewModel.getProductTrending()
        homeViewModel.trending.observe(viewLifecycleOwner){
            showProductTrending(it)
        }
    }

    private fun populateDataAtricle(dataArticle: Article?) {
        if (dataArticle != null){
            with(binding){
                Glide.with(this@DetailArticleFragment)
                    .load(dataArticle.photo)
                    .transform(CenterInside(),RoundedCorners(16))
                    .into(ivBanner)
                tvTitle.text = dataArticle.title
                tvWaktuArtikel.text = dataArticle.date
                tvDesc.text = dataArticle.content
            }
        } else {
            Utils.peringatan(requireContext(), "Koneksi tidak stabil, Terjadi kesalahan")
            requireActivity().onBackPressed()
        }
    }

    private fun showProductTrending(listProductTrending: List<Trending>){
        val listTrendingAdapter = ListTrendingAdapter(listProductTrending)
        binding.listProdukTrending.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = listTrendingAdapter
        }
    }

    companion object {
        const val EXTRA_ARTICLE = "article"
    }

}