package com.apps.finalproject.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.finalproject.data.api.ApiServices
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.ProfilItem
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.ui.favorite.FavoriteActivity
import com.apps.finalproject.ui.view.DaftarAlamatActivity
import com.apps.finalproject.ui.view.ProfileActivity
import com.apps.finalproject.ui.view.RiwayatPembelianActivity
import com.apps.finalproject.ui.view.VoucerActivity
import com.apps.finalproject.utils.Utils.peringatan
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProfileviewModel(private val repository: MainRepository) : ViewModel() {
    private val PI = MutableLiveData<List<ProfilItem>>()

    fun setProfilItems(context: Context) {
        val daftarAlamat = Intent(context, DaftarAlamatActivity::class.java)
        val riwayatPembelian = Intent(context, DaftarAlamatActivity::class.java)
        val Voucerku = Intent(context, VoucerActivity::class.java)
        val ArtikelTersimpan = Intent(context, FavoriteActivity::class.java)

        val itemProfils: MutableList<ProfilItem> = mutableListOf()
        itemProfils.add(ProfilItem("ic_marker","Daftar Alamat",daftarAlamat))
        itemProfils.add(ProfilItem("ic_clipboard","Riwayat Pembelian",riwayatPembelian))
        itemProfils.add(ProfilItem("ic_voucer","Voucer Ku",Voucerku))
        itemProfils.add(ProfilItem("ic_favorite_profil","Favorit",ArtikelTersimpan))

        PI.postValue(itemProfils)
    }

    internal fun getProfilItems(): LiveData<List<ProfilItem>> {
        return PI
    }
}