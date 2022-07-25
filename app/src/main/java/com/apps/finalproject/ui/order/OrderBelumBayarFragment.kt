package com.apps.finalproject.ui.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.apps.finalproject.databinding.FragmentOrderBelumBayarBinding
import com.apps.finalproject.remote.response.DataOrders
import com.apps.finalproject.remote.response.VariantsData
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.OrdersAdapter
import com.apps.finalproject.ui.viewmodel.VariantViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import okhttp3.internal.notify

class OrderBelumBayarFragment : Fragment() {
    private val ordersViewModel:OrdersViewModel by activityViewModels()

//    private val variantsViewModel:VariantViewModel by viewModels {
//        ViewModelFactory.getInstance(requireParentFragment().requireContext())
//    }
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
        var cek = 0
        var total = 0
        ordersViewModel.getResponse().observe(requireActivity()){
            if ((it == null) or (it.isNullOrEmpty())){
                binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
                binding.progresOrder.visibility = View.GONE
            }else{
                adapters.clear()
                adapters.notifyDataSetChanged()
                Log.d("order",it?.get(0).toString())
                val size = it?.size
                it?.forEach { mOrders ->
                    total+=1
                    if(mOrders.status == "WAITING_FOR_PAYMENT"){
                        cek+=1
                        adapters.add(OrdersAdapter(mOrders,0))
                    }
                    if ((size == total) and (cek == 0)){
                        binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
                        binding.progresOrder.visibility = View.GONE
                    }
                }
                binding.rvOrdersBelumBayar.adapter = adapters
                binding.progresOrder.visibility = View.GONE
            }
        }
    }

}