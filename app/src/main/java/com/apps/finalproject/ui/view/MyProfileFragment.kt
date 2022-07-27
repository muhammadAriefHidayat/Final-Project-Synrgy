package com.apps.finalproject.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityProfileBinding
import com.apps.finalproject.databinding.FragmentMyProfileBinding
import com.apps.finalproject.remote.model.ProfilItem
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ProfilAdapter
import com.apps.finalproject.ui.favorite.FavoriteActivity
import com.apps.finalproject.ui.viewmodel.ProfileviewModel
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils
import com.bumptech.glide.util.Util
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import okhttp3.internal.notify

class MyProfileFragment : Fragment() {

    private val profileViewModel: ProfileviewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    lateinit var binding: FragmentMyProfileBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.clear()
        adapter.notifyDataSetChanged()
        setData()
        binding.apply {
            btnEditProfile.setOnClickListener {
                if (AppPref.token == ""){
                    Utils.peringatan(requireContext(),"harap login terlebih dahulu")
                }else{
                    startActivity(Intent(requireActivity(),EditProfileActivity::class.java))
                }
            }
            if (AppPref.username == ""){
                tvName.text = "Silahkan Login"
                tvPhone.text = "Silahkan Login"
                tvEmailProfil.text = "Silahkan Login"
                tvUsername.text = "Silahkan Login"
                btnLogoutProfile.text = "Login"
            }else{
                tvName.text = AppPref.username
                tvUsername.text = "Customer"
                tvPhone.text = AppPref.nomor
                tvEmailProfil.text = AppPref.email
                btnLogoutProfile.text = "Logout"
            }
            btnLogoutProfile.setOnClickListener {
                AppPref.token = ""
                AppPref.username = ""
                AppPref.email = ""
                AppPref.nomor = ""
                val intents = Intent(requireContext(), AuthActivity::class.java)
                startActivity(intents)
            }
        }
    }


    fun setData(){
        val daftarAlamat = Intent(requireActivity(), AddressListActivity::class.java)
        val riwayatPembelian = Intent(requireActivity(), DaftarAlamatActivity::class.java)
        val Voucerku = Intent(requireActivity(), VoucerActivity::class.java)
        val ArtikelTersimpan = Intent(requireActivity(), FavoriteActivity::class.java)

        val itemProfils: MutableList<ProfilItem> = mutableListOf()
        itemProfils.add(ProfilItem("ic_marker","Daftar Alamat",daftarAlamat))
        itemProfils.add(ProfilItem("ic_clipboard","Riwayat Pembelian",riwayatPembelian))
        itemProfils.add(ProfilItem("ic_voucer","Voucer Ku",Voucerku))
        itemProfils.add(ProfilItem("ic_favorite_profil","Favorit",ArtikelTersimpan))

        itemProfils.forEach { profile ->
            adapter.add(ProfilAdapter(profile))
        }
        binding.rvProfil.adapter = adapter
    }
}
