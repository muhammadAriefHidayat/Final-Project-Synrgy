package com.apps.finalproject.remote.model

import android.content.Intent
import com.google.gson.annotations.SerializedName

class EkspedisiItem(@field:SerializedName("img")
                        var img: String, @field:SerializedName("itemEkspedisi")
                        var kurir: String)