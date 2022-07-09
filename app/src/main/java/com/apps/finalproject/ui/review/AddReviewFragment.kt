package com.apps.finalproject.ui.review

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentAddReviewBinding
import com.bumptech.glide.Glide

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data!!.data
//
//            CropImage.activity(imageUri)
//                .setAspectRatio(1, 1)
//                .start(this)
        }

//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            val result = CropImage.getActivityResult(data)
//
//            if (resultCode == Activity.RESULT_OK) {
//                progress.visibility = View.VISIBLE
//
//                val resultUri = result.uri
//                val nama = nama_cluster.text.toString()
//
//                val filePath = mStorageRef!!.child("perumahan_profil_images")
//                    .child("$nama.jpg")
//
//                /**
//                 * mengupload file berupa URI image dan memanggil fungsi savetofirebase
//                 * */
//                filePath.putFile(resultUri).addOnSuccessListener {
//                    filePath.downloadUrl.addOnSuccessListener {
//                        Log.d("SettingActivity", "$it")
//                        foto = it.toString()
//                        Glide.with(this)
//                            .load(it)
//                            .placeholder(R.drawable.ic_home_work_24)
//                            .into(cluster_foto);
//                        progress.visibility = View.INVISIBLE
//                    }
//                }
//            }
//        }
    }
}