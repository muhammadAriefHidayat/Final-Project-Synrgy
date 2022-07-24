package com.apps.finalproject.ui.checkoutshiping

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.apps.finalproject.ui.order.OrderBelumBayarFragment
import com.apps.finalproject.ui.order.OrderDikirimFragment
import com.apps.finalproject.ui.order.OrderDiprosesFragment
import com.apps.finalproject.ui.order.OrderSelesaiFragment


class SectionAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0-> return OrderBelumBayarFragment()
            1 -> return OrderDiprosesFragment()
            2-> return OrderDikirimFragment()
            3 -> return OrderSelesaiFragment()
        }
        return null!!
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Belum Bayar"
            1-> return "Diproses"
            2 -> return "Dikirim"
            3-> return "Selesai"
        }
        return null
    }
}