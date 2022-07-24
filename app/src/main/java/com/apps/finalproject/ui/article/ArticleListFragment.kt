package com.apps.finalproject.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentArticleListBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.home.HomeViewModel
import com.apps.finalproject.utils.objectToString

class ArticleListFragment : Fragment() {

    private lateinit var binding: FragmentArticleListBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getArticle()
        homeViewModel.article.observe(viewLifecycleOwner){
            showDataArticle(it)
        }

    }

    private fun showDataArticle(dataArticle: List<Article>) {
        val listArticleAdapter = ListArticleAdapter(dataArticle)
        listArticleAdapter.setOnItemClickListener(onItemClick)
        binding.listArticle.apply {
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
        val bundle = bundleOf(DetailArticleFragment.EXTRA_ARTICLE to objectToString(article))
        Navigation.findNavController(requireView()).navigate(R.id.action_articleListFragment_to_detailArticleFragment, bundle)
    }

}