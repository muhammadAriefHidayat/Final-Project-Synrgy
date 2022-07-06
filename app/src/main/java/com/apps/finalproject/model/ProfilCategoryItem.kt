package com.apps.finalproject.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ProfilCategoryItem {

    val profilItemList: ArrayList<ProfilItem>
        get() = Gson().fromJson(
            profilItems,
            object : TypeToken<ArrayList<ProfilItem>>() {
            }.type
        )

    var profilItems = "[\n" +
            "  {\n" +
            "    \"img\":\"ic_marker\",\n" +
            "    \"itemProfil\":\"Daftar Alamat\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"ic_voucer\",\n" +
            "    \"image\":\"Riwayat Pembelian\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"ic_marker\",\n" +
            "    \"image\":\"Voucherku\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"ic_voucer\",\n" +
            "    \"image\":\"Artikel Tersimpan\"\n" +
            "  }\n" +
            "]"
}
