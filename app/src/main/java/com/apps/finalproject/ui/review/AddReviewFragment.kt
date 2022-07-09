package com.apps.finalproject.ui.review

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentAddReviewBinding

class AddReviewFragment : Fragment() {

    private lateinit var binding : FragmentAddReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddReviewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val coment = edtComment.text.toString()
            when {
                coment.isEmpty()->{
                    edtComment.error = "Masukkan Email"
                }else ->{

                }
            }
            addPhoto.setOnClickListener {
                val intentGalery = Intent(Intent.ACTION_PICK)
                intentGalery.type = "image/*"
                startActivityForResult(Intent.createChooser(intentGalery, "Pilih foto"), 0)
            }
            addPhoto2.setOnClickListener {
                val intentGalery = Intent(Intent.ACTION_PICK)
                intentGalery.type = "image/*"
                startActivityForResult(Intent.createChooser(intentGalery, "Pilih foto"), 3)
            }
        }
    }

}