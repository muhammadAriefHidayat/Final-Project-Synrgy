package com.apps.finalproject.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.databinding.ActivityProfileBinding
import com.apps.finalproject.remote.model.ProfilItem
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

        val daftarAlamat = Intent(this,DaftarAlamatActivity::class.java)
        val riwayatPembelian = Intent(this,DaftarAlamatActivity::class.java)
        val Voucerku = Intent(this,VoucerActivity::class.java)
        val ArtikelTersimpan = Intent(this,VoucerActivity::class.java)

        val itemProfils: MutableList<ProfilItem> = mutableListOf()
        itemProfils.add(ProfilItem("ic_marker","Daftar Alamat",daftarAlamat))
        itemProfils.add(ProfilItem("ic_clipboard","Riwayat Pembelian",riwayatPembelian))
        itemProfils.add(ProfilItem("ic_voucer","Voucer Ku",Voucerku))
        itemProfils.add(ProfilItem("ic_bookmark","Artikel tersimpan",ArtikelTersimpan))

        itemProfils.forEach {
            adapter.add(ProfilAdapter(it, this))
        }

        adapter.setOnItemClickListener { item, view ->

        }
        binding.rvProfil.adapter = adapter

        binding.apply {
            tvName.text = AppPref.username
            tvEmailProfil.text = AppPref.email
            btnLogoutProfile.setOnClickListener {
                startActivity(Intent(this@ProfileActivity,AuthActivity::class.java))
            }
        }

    }


    private fun initUI() {

    }

}