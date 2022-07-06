package com.apps.finalproject.model

import com.google.gson.annotations.SerializedName

class ProfilItem(@field:SerializedName("img")
                        var img: String, @field:SerializedName("itemProfil")
                        var itemProfil: String)