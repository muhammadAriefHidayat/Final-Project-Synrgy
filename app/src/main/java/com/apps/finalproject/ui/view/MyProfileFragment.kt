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
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ProfilAdapter
import com.apps.finalproject.ui.viewmodel.ProfileviewModel
import com.apps.finalproject.utils.AppPref
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

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

        profileViewModel.setProfilItems(requireContext())
        profileViewModel.getProfilItems().observe(requireActivity()) {
            it.forEach { profile ->
                adapter.add(ProfilAdapter(profile, requireContext()))
            }
        }

        binding.rvProfil.adapter = adapter

        binding.apply {
            tvName.text = AppPref.username
            tvEmailProfil.text = AppPref.email
            btnLogoutProfile.setOnClickListener {
                AppPref.token = ""
                AppPref.username = ""
                AppPref.email = ""
                val intents = Intent(requireContext(), AuthActivity::class.java)
                startActivity(intents)
            }
        }

    }
}
