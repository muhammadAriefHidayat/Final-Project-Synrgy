package com.apps.finalproject.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.databinding.ActivityProfileBinding
import com.apps.finalproject.model.ProfilCategoryItem
import com.apps.finalproject.model.ProfilItem
import com.apps.finalproject.ui.adapter.ProfilAdapter
import com.apps.finalproject.utils.AppPref
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val itemProfils: MutableList<ProfilItem> = mutableListOf()

        itemProfils.add(ProfilItem("ic_marker","Daftar Alamat"))
        itemProfils.add(ProfilItem("ic_clipboard","Riwayat Pembelian"))
        itemProfils.add(ProfilItem("ic_voucer","Voucer Ku"))
        itemProfils.add(ProfilItem("ic_bookmark","Artikel tersimpan"))

        itemProfils.forEach {
            adapter.add(ProfilAdapter(it, this))
        }

        binding.apply {
            tvName.text = AppPref.username
            tvEmailProfil.text = AppPref.email
        }
        binding.rvProfil.adapter = adapter
    }


    private fun initUI() {

    }

}