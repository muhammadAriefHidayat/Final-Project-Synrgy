package com.apps.finalproject.ui.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentOrderBelumBayarBinding
import com.apps.finalproject.databinding.FragmentOrderDikirimBinding
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.KurirAdapter
import com.apps.finalproject.ui.adapter.OrdersAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class OrderBelumBayarFragment : Fragment() {
    private val ordersViewModel:OrdersViewModel by activityViewModels()
    lateinit var binding: FragmentOrderBelumBayarBinding
    private val adapters = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentOrderBelumBayarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersViewModel.getResponse().observe(requireActivity()){
            if ((it == null) or (it.isNullOrEmpty())){
                binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
            }else{
                binding.layoutKosong.constraintKosong.visibility = View.GONE
                Log.d("order",it?.get(0).toString())
                it?.forEach { mOrders ->
                    adapters.add(OrdersAdapter(mOrders, requireContext()))
                }
                binding.rvOrdersBelumBayar.adapter = adapters
            }
        }
    }
}