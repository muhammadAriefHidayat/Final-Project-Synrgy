package com.apps.finalproject.ui.beautyfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentBeautyFeedBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.article.DetailArticleFragment
import com.apps.finalproject.ui.article.ListArticleAdapter
import com.apps.finalproject.ui.home.HomeViewModel
import com.apps.finalproject.utils.objectToString

class BeautyFeedFragment : Fragment() {

    private lateinit var binding: FragmentBeautyFeedBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeautyFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getArticle()
        homeViewModel.article.observe(viewLifecycleOwner){
            showDataArticle(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun showDataArticle(it: List<Article>) {
            val listBeautyFeedAdapter = ListBeautyFeedAdapter(it)
            listBeautyFeedAdapter.setOnItemClickListener(onItemClick)
            binding.itemListBeautyFeedFragment.apply {
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                adapter = listBeautyFeedAdapter
            }
    }

    private val onItemClick = object : ListBeautyFeedAdapter.OnItemClickListener{
        override fun onItemClick(article: Article) {
            detailArticle(article)
        }
    }

    private fun detailArticle(article: Article) {
        val bundle = bundleOf(DetailArticleFragment.EXTRA_ARTICLE to objectToString(article))
        Navigation.findNavController(requireView()).navigate(R.id.action_BeautyFeedFragment_to_detailArticleFragment, bundle)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}