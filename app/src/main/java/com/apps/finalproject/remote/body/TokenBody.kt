package com.apps.finalproject.remote.body

import com.google.gson.annotations.SerializedName

data class TokenBody(
	@SerializedName("token")
	val token: String
)
