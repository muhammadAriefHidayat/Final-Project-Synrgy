package com.apps.finalproject.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.apps.finalproject.databinding.FragmentOrdersBinding
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.checkoutshiping.SectionAdapter
import com.apps.finalproject.ui.order.OrdersViewModel

class OrdersFragment : Fragment() {
    private var sectionpageAdapter: SectionAdapter? = null
    lateinit var binding: FragmentOrdersBinding

    private val ordersViewModel: OrdersViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireParentFragment().requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sectionpageAdapter = SectionAdapter(childFragmentManager)
        binding.dashViewPagger.adapter = sectionpageAdapter
        binding.tablayoutid.setupWithViewPager(binding.dashViewPagger)
        ordersViewModel.getOrders()
    }
}