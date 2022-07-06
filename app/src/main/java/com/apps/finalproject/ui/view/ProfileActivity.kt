package com.apps.finalproject.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.databinding.ActivityProfileBinding
import com.apps.finalproject.model.ProfilCategoryItem
import com.apps.finalproject.model.ProfilItem
import com.apps.finalproject.ui.adapter.ProfilAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ProfileActivity : AppCompatActivity() {
    private lateinit var itemArrayList: ArrayList<ProfilItem>
    lateinit var binding: ActivityProfileBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        itemArrayList = ProfilCategoryItem.profilItemList
        itemArrayList.forEach {
            adapter.add(ProfilAdapter(it, this))
        }

        binding.apply {
            rvProfil.adapter = adapter
        }
    }
}