package com.apps.finalproject.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Constructor

data class PaymentResponse(

    @field:SerializedName("data")
    val data: DataPayment,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

@Parcelize
data class DataPayment(
    @field:SerializedName("bankName")
    val bankName: String,
    @field:SerializedName("orderId")
    val orderId: String,
    @field:SerializedName("transactionId")
    val transactionId: String,
    @field:SerializedName("paymentType")
    val paymentType: String,
    @field:SerializedName("transactionStatus")
    val transactionStatus: String,
    @field:SerializedName("vaNumber")
    val vaNumber: String,
    @field:SerializedName("grossAmount")
    val grossAmount: Int,
	@field:SerializedName("transactionTime")
    val transactionTime: Long,
    @field:SerializedName("expiryTime")
    val expiryTime: Long) : Parcelable{
    constructor():this("","","","","","",-1,-1,-1)
    }
