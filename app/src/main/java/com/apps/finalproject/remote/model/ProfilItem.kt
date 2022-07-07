package com.apps.finalproject.remote.model

import android.content.Intent
import com.google.gson.annotations.SerializedName

class ProfilItem(@field:SerializedName("img")
                        var img: String, @field:SerializedName("itemProfil")
                        var itemProfil: String,var intent:Intent)