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
import com.apps.finalproject.remote.response.DataOrders
import com.apps.finalproject.ui.adapter.OrdersAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class OrderDikirimFragment : Fragment() {

    private val ordersViewModel: OrdersViewModel by activityViewModels()
    lateinit var binding: FragmentOrderDikirimBinding
    private val adapters = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDikirimBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var cek = 0
        var total = 0

        ordersViewModel.getResponse().observe(requireActivity()) {
            if ((it == null) or (it.isNullOrEmpty())){
                binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
            }else{
                adapters.clear()
                adapters.notifyDataSetChanged()
                Log.d("order",it?.get(0).toString())
                val size = it?.size
                it?.forEach { mOrders ->
                    total+=1
                    if(mOrders.status == "ON_DELIVERY"){
                        cek+=1
                        setData(mOrders)
                    }
                    if ((size == total) and (cek == 0)){
                        binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
                        binding.progresOrder.visibility = View.GONE
                    }
                }
                binding.rvOrdersDikirim.adapter = adapters
                binding.progresOrder.visibility = View.GONE
            }
        }
    }

    private fun setData(mOrders: DataOrders) {
        adapters.add(OrdersAdapter(mOrders,1,requireParentFragment().requireContext()))
    }
}