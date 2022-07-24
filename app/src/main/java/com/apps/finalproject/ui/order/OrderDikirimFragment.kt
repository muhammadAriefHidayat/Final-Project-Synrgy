package com.apps.finalproject.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentOrderDikirimBinding

class OrderDikirimFragment : Fragment() {

    private val ordersViewModel: OrdersViewModel by activityViewModels()
    lateinit var binding: FragmentOrderDikirimBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDikirimBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersViewModel.getResponse().observe(requireActivity()) {
            if ((it == null) or (it.isNullOrEmpty())){
                binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
            }else{
                binding.layoutKosong.constraintKosong.visibility = View.GONE
            }
        }
    }
}